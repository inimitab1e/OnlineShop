package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale

interface HomePageRepository {

    suspend fun getCategoriesList(): CategoriesList

    suspend fun getLatestList(): List<Latest>?

    suspend fun getSaleList(): List<FlashSale>?

    suspend fun getBrandsList(): BrandsList
}