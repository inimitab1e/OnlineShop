package com.example.onlineshop.data.repositories

import android.util.Patterns
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.authentication.ValidationResult
import com.example.onlineshop.domain.repositories.ValidationRepository

class ValidationRepositoryImpl : ValidationRepository {

    override fun validateEmail(email: String): ValidationResult {
        return if (email.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = StringConstants.emptyEmailErrorMessage
            )
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(
                successful = false,
                errorMessage = StringConstants.invalidEmailErrorMessage
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

    override fun validatePassword(password: String): ValidationResult {
        if(password.length < minimumPasswordLength) {
            return ValidationResult(
                successful = false,
                errorMessage = StringConstants.shortPasswordErrorMessage
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = StringConstants.invalidPasswordErrorMessage
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    override fun validateNames(userName: String): ValidationResult {
        return if (userName.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = StringConstants.emptyUsernameErrorMessage
            )
        } else {
            ValidationResult(
                successful = true
            )
        }
    }

    companion object {
        const val minimumPasswordLength = 8
    }
}