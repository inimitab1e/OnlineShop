package com.example.onlineshop.presentation.ui.registration

import androidx.lifecycle.ViewModel
import com.example.onlineshop.data.model.authentication.AuthenticationFormState
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validationRepository: ValidationRepository
) : ViewModel() {

    private val _errorValidationFormsMessage =
        MutableSharedFlow<AuthenticationFormState>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val errorValidationFormsMessage: SharedFlow<AuthenticationFormState> =
        _errorValidationFormsMessage.asSharedFlow()

    fun validateAndRegister(firstName: String, lastName: String, email: String, password: String) {
        val emailResult = validationRepository.validateEmail(email)
        val passwordResult = validationRepository.validatePassword(password)
        val firstNameResult = validationRepository.validateNames(firstName)
        val lastNameResult = validationRepository.validateNames(lastName)

        val hasError = listOf(
            emailResult,
            passwordResult,
            firstNameResult,
            lastNameResult
        ).any { !it.successful }

        if (hasError) {
            _errorValidationFormsMessage.tryEmit(
                AuthenticationFormState(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    firstNameError = firstNameResult.errorMessage,
                    lastNameError = lastNameResult.errorMessage
                )
            )
        } else {
            //TODO REGISTRATION
        }
    }
}