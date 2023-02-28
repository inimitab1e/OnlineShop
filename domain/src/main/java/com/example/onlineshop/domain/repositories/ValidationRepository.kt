package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.authentication.ValidationResult

interface ValidationRepository {

    fun validateEmail(email: String): ValidationResult

    fun validatePassword(password: String): ValidationResult

    fun validateNames(userName: String): ValidationResult
}