package com.example.onlineshop.domain.repositories

interface AuthenticationRepository {

    suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): String

    suspend fun initLogin(
        email: String,
        password: String
    ): String

    suspend fun deleteLocalUser()

    fun findLatestUser(): Boolean
}