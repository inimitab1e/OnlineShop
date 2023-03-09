package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.data.local.room.AppDatabaseDAO
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.repositories.ProfileRepository
import com.example.onlineshop.data.toCurrentUserInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val appDatabaseDAO: AppDatabaseDAO,
    private val preferenceHelper: PreferenceHelper,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override suspend fun getCurrentUserInfo(): CurrentUserInfo =
        withContext(ioDispatcher) {
            val firstName = preferenceHelper.getFirstName()
            checkNotNull(firstName)
            return@withContext appDatabaseDAO.getAllUserInfo(firstName).toCurrentUserInfo()
        }
}