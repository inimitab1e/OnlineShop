package com.example.onlineshop.presentation.ui.registration

import androidx.lifecycle.*
import com.example.onlineshop.data.model.authentication.AuthenticationFormState
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import com.example.onlineshop.domain.repositories.ValidationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val validationRepository: ValidationRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _errorValidationFormsMessage = MutableLiveData<AuthenticationFormState?>()
    val errorValidationFormsMessage: LiveData<AuthenticationFormState?> get() = _errorValidationFormsMessage

    private val _registrationResponse = MutableLiveData("")
    val registrationResponse: LiveData<String?> get() = _registrationResponse

    fun validateAndRegister(firstName: String, lastName: String, email: String) {
        val firstNameResult = validationRepository.validateNames(firstName)
        val lastNameResult = validationRepository.validateNames(lastName)
        val emailResult = validationRepository.validateEmail(email)

        val hasError = listOf(
            firstNameResult, lastNameResult, emailResult
        ).any { !it.successful }

        if (hasError) {
            _errorValidationFormsMessage.postValue(
                AuthenticationFormState(
                    emailError = emailResult.errorMessage,
                    firstNameError = firstNameResult.errorMessage,
                    lastNameError = lastNameResult.errorMessage
                )
            )
        } else {
            doRegistration(firstName, lastName, email)
        }
    }

    private fun doRegistration(
        firstName: String,
        lastName: String,
        email: String
    ) {
        viewModelScope.launch {
            _registrationResponse.postValue(
                authenticationRepository.initRegistration(
                    firstName,
                    lastName,
                    email
                )
            )
        }
    }
}