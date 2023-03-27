package com.example.onlineshop.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import com.example.onlineshop.domain.repositories.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val initialUserInfo: LiveData<CurrentUserInfo?> = liveData {
        emit(profileRepository.getCurrentUserInfo())
    }

    fun doLogout() {
        viewModelScope.launch {
             authenticationRepository.clearPreferences()
        }
    }
}