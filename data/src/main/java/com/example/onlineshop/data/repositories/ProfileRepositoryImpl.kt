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
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val appDatabaseDAO: AppDatabaseDAO,
    private val preferenceHelper: PreferenceHelper,
    private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override fun getCurrentUserInfo(): Flow<CurrentUserInfo> {
        val email = preferenceHelper.getEmail()
        checkNotNull(email)
        return appDatabaseDAO.getAllUserInfo(email).map { userInfo ->
            userInfo.toCurrentUserInfo()
        }.flowOn(ioDispatcher)
    }
}