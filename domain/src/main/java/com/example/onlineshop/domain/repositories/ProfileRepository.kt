package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.CurrentUserInfo
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getCurrentUserInfo(): CurrentUserInfo
}