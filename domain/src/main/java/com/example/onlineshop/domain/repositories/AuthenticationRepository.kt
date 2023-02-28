package com.example.onlineshop.domain.repositories

interface AuthenticationRepository {

    suspend fun initRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean

    suspend fun initLogin(
        firstName: String,
        password: String
    ): Boolean
}