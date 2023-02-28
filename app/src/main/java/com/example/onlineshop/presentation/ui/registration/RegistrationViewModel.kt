package com.example.onlineshop.presentation.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.data.model.authentication.AuthenticationFormState
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validationRepository: ValidationRepository
) : ViewModel() {

    private val _errorValidationFormsMessage = MutableLiveData<AuthenticationFormState>()
    val errorValidationFormsMessage: LiveData<AuthenticationFormState> get() = _errorValidationFormsMessage

    fun validateAndRegister(firstName: String, lastName: String, email: String, password: String) {
        val emailResult = validationRepository.validateEmail(email)
        val passwordResult = validationRepository.validatePassword(password)
        val firstNameResult = validationRepository.validateNames(firstName)
        val lastNameResult = validationRepository.validateNames(lastName)

        val hasError = listOf(
            emailResult, passwordResult, firstNameResult, lastNameResult
        ).any { !it.successful }

        if (hasError) {
            _errorValidationFormsMessage.postValue(
                AuthenticationFormState(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    firstNameError = firstNameResult.errorMessage,
                    lastNameError = lastNameResult.errorMessage
                )
            )
        } else {
            doRegistration(firstName, lastName, email, password)
        }
    }

    private fun doRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {

    }
}