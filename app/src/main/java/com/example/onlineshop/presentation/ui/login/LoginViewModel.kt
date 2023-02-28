package com.example.onlineshop.presentation.ui.login

import androidx.lifecycle.ViewModel
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationRepository: ValidationRepository
) : ViewModel() {

    //TODO
}