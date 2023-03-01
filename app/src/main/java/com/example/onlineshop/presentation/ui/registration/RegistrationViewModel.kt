package com.example.onlineshop.presentation.ui.registration

import androidx.lifecycle.*
import com.example.onlineshop.data.model.authentication.AuthenticationFormState
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validationRepository: ValidationRepository,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val isLatestUserExists: LiveData<Boolean?> = liveData {
        emit(authenticationRepository.findLatestUser())
    }

    private val _errorValidationFormsMessage = MutableLiveData<AuthenticationFormState?>()
    val errorValidationFormsMessage: LiveData<AuthenticationFormState?> get() = _errorValidationFormsMessage

    private val _registrationResponse = MutableLiveData("")
    val registrationResponse: LiveData<String?> get() = _registrationResponse

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
        viewModelScope.launch {
            _registrationResponse.postValue(
                authenticationRepository.initRegistration(
                    firstName,
                    lastName,
                    email,
                    password
                )
            )
        }
    }
}