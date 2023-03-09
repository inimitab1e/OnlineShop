package com.example.onlineshop.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import com.example.onlineshop.domain.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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