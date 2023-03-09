package com.example.onlineshop.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _loginResponse = MutableLiveData("")
    val loginResponse: LiveData<String?> get() = _loginResponse

    fun loginWithPassword(firstName: String) {
        viewModelScope.launch {
            _loginResponse.postValue(authenticationRepository.initLogin(firstName))
        }
    }
}