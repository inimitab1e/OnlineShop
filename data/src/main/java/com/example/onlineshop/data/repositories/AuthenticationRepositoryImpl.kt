package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.data.local.AppDatabaseDAO
import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val appDatabaseDAO: AppDatabaseDAO,
    private val ioDispatcher: CoroutineDispatcher,
    private val preferenceHelper: PreferenceHelper
) : AuthenticationRepository {

    override suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String
    ): String {
        return withContext(ioDispatcher) {
            val isUserExists = appDatabaseDAO.checkIfUserExists(firstName)
            if (!isUserExists) {
                preferenceHelper.saveFirstName(firstName)
                appDatabaseDAO.tryRegistration(
                    Users(
                        firstName = firstName,
                        lastName = lastName,
                        email = email
                    )
                )
                return@withContext StringConstants.registrationSuccessfulMessage
            } else {
                return@withContext StringConstants.userAlreadyExistsMessage
            }
        }
    }

    override suspend fun initLogin(firstName: String): String {
        return withContext(ioDispatcher) {
            val isUserExists = appDatabaseDAO.checkIfUserExists(firstName)
            if (isUserExists) {
                preferenceHelper.saveFirstName(firstName)
                return@withContext StringConstants.loginSuccessfulMessage
            } else {
                return@withContext StringConstants.userDoesNotExists
            }
        }
    }

    override suspend fun clearPreferences() = preferenceHelper.clearFirstName()
}