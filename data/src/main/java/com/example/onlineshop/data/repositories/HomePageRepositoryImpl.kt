package com.example.onlineshop.data.repositories

import android.content.Context
import com.example.onlineshop.data.model.categories.CategoriesListDto
import com.example.onlineshop.data.toCategoriesList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.repositories.HomePageRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

class HomePageRepositoryImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
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
}