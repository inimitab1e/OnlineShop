package com.example.onlineshop.data.repositories

import com.example.onlineshop.data.network.ApiService
import com.example.onlineshop.data.toItemDescription
import com.example.onlineshop.domain.network_utils.result.Result
import com.example.onlineshop.domain.model.description.ItemDescription
import com.example.onlineshop.domain.network_utils.result.asSuccess
import com.example.onlineshop.domain.repositories.ItemDescriptionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemDescriptionRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : ItemDescriptionRepository {

    override suspend fun getItemDescription(): ItemDescription? =
        withContext(ioDispatcher) {
            when (val response = apiService.getRemoteItemDescriptionData()) {
                is Result.Success -> return@withContext response.asSuccess().value.toItemDescription()
                is Result.Failure<*> -> return@withContext null
            }
        }
}