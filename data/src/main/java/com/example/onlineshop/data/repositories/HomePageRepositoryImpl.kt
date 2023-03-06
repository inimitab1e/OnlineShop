package com.example.onlineshop.data.repositories

import android.content.Context
import com.example.onlineshop.data.model.brands.BrandsListDto
import com.example.onlineshop.data.model.categories.CategoriesListDto
import com.example.onlineshop.data.network.ApiService
import com.example.onlineshop.data.toBrandsList
import com.example.onlineshop.data.toCategoriesList
import com.example.onlineshop.data.toFlashSale
import com.example.onlineshop.data.toLatest
import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.network_utils.result.Result
import com.example.onlineshop.domain.model.sale.FlashSale
import com.example.onlineshop.domain.model.search.SearchResponse
import com.example.onlineshop.domain.network_utils.result.asSuccess
import com.example.onlineshop.domain.repositories.HomePageRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class HomePageRepositoryImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : HomePageRepository {

    override suspend fun getCategoriesList(): CategoriesList =
        withContext(ioDispatcher) {
            val jsonString = context.assets.open("categories.json")
                .bufferedReader()
                .use { it.readText() }
            val gson = Gson()
            val objectItemType: Type = object : TypeToken<CategoriesListDto>() {}.type
            val item = gson.fromJson<CategoriesListDto>(jsonString, objectItemType)
            return@withContext item.toCategoriesList()
        }

    override suspend fun getLatestList(): List<Latest>? =
        withContext(ioDispatcher) {
            when (val response = apiService.getRemoteLatestData()) {
                is Result.Success -> {
                    return@withContext response.asSuccess().value.latest.map { value ->
                        value.toLatest()
                    }
                }
                is Result.Failure<*> -> return@withContext null
            }
        }

    override suspend fun getSaleList(): List<FlashSale>? =
        withContext(ioDispatcher) {
            when (val response = apiService.getRemoteSaleData()) {
                is Result.Success -> {
                    return@withContext response.asSuccess().value.flash_sale.map { value ->
                        value.toFlashSale()
                    }
                }
                is Result.Failure<*> -> return@withContext null
            }
        }

    override suspend fun getBrandsList(): BrandsList =
        withContext(ioDispatcher) {
            val jsonString = context.assets.open("brands.json")
                .bufferedReader()
                .use { it.readText() }
            val gson = Gson()
            val objectItemType: Type = object : TypeToken<BrandsListDto>() {}.type
            val item = gson.fromJson<BrandsListDto>(jsonString, objectItemType)
            return@withContext item.toBrandsList()
        }

    override suspend fun doSearch(query: String): SearchResponse? =
        withContext(ioDispatcher) {
            when (val listOfWords = apiService.getRemoteListOfWords()) {
                is Result.Success -> {
                    return@withContext SearchResponse(
                        searchResult = listOfWords.asSuccess().value.words.filter { word ->
                            word.startsWith(query, ignoreCase = true)
                        })
                }
                is Result.Failure<*> -> return@withContext null
            }
        }
}