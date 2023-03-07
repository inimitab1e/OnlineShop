package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.local.AppDatabaseDAO
import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class AuthenticationRepositoryImpl @Inject constructor(
    private val appDatabaseDAO: AppDatabaseDAO,
    private val ioDispatcher: CoroutineDispatcher,
    private val preferenceHelper: PreferenceHelper
) : AuthenticationRepository {

    override suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): String {
        return withContext(ioDispatcher) {
            val isUserExists = appDatabaseDAO.checkIfUserExists(email)
            if (!isUserExists) {
                preferenceHelper.saveEmail(email)
                appDatabaseDAO.tryRegistration(
                    Users(
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password
                    )
                )
                return@withContext StringConstants.registrationSuccessfulMessage
            } else {
                return@withContext StringConstants.userAlreadyExistsMessage
            }
        }
    }

    override suspend fun initLogin(email: String, password: String): String {
        return withContext(ioDispatcher) {
            val savedPassword = appDatabaseDAO.tryLoginWithPassword(email)
                ?: return@withContext StringConstants.userDoesNotExists
            if (password == savedPassword) {
                preferenceHelper.saveEmail(email)
                return@withContext StringConstants.loginSuccessfulMessage
            } else {
                return@withContext StringConstants.loginFailedMessage
            }
        }
    }

    override fun deleteLocalUser() {
        preferenceHelper.clearEmail()
    }

    override suspend fun clearPreferences() = preferenceHelper.clearEmail()
}