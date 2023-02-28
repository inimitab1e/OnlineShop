package com.example.onlineshop.data.model.authentication

data class AuthenticationFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val firstNameError: String? = null,
    val lastNameError: String? = null
)
