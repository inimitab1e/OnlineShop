package com.example.onlineshop.domain.repositories

interface AuthenticationRepository {

    suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String
    ): String

    suspend fun initLogin(
        firstName: String
    ): String

    suspend fun clearPreferences()
}