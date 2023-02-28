package com.example.onlineshop.domain.model.authentication

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)