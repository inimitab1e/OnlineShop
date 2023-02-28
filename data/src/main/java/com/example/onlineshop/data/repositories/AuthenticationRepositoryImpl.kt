package com.example.onlineshop.data.repositories

import com.example.onlineshop.domain.local.AppDatabaseDAO
import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabaseDAO,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {

    override suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean = withContext(ioDispatcher) {
        if (appDatabase.checkIfUserExists(firstName)) {
            appDatabase.tryRegistration(
                Users(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
            )
            true
        } else {
            false
        }
    }

    override suspend fun initLogin(firstName: String, password: String): Boolean =
        withContext(ioDispatcher) {
            val savedPassword = appDatabase.tryLoginWithPassword(firstName)
            password == savedPassword
        }
}