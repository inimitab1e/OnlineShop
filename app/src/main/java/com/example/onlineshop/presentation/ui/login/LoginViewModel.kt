package com.example.onlineshop.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _loginResponse = MutableStateFlow("")
    val loginResponse: StateFlow<String> = _loginResponse.asStateFlow()

    fun loginWithPassword(email: String, password: String) {
        viewModelScope.launch {
            _loginResponse.value = authenticationRepository.initLogin(email, password)
        }
    }
}