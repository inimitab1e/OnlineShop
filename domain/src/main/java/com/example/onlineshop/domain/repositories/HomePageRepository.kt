package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale
import com.example.onlineshop.domain.model.search.SearchResponse

interface HomePageRepository {

    suspend fun getCategoriesList(): CategoriesList

    suspend fun getLatestList(): List<Latest>?

    suspend fun getSaleList(): List<FlashSale>?

    suspend fun getBrandsList(): List<BrandsList>

    suspend fun doSearch(query: String): SearchResponse?
}