package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.domain.local.AppDatabaseDAO
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.repositories.ProfileRepository
import com.example.onlineshop.data.toCurrentUserInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class ProfileRepositoryImpl @Inject constructor(
    private val appDatabaseDAO: AppDatabaseDAO,
    private val preferenceHelper: PreferenceHelper,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getCurrentUserInfo(): CurrentUserInfo =
        withContext(ioDispatcher) {
            val email = preferenceHelper.getEmail()
            checkNotNull(email)
            return@withContext appDatabaseDAO.getAllUserInfo(email).toCurrentUserInfo()
        }
}