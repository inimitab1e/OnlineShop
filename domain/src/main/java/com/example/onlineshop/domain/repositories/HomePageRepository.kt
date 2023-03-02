package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.categories.CategoriesList

interface HomePageRepository {

    suspend fun getCategoriesList(): CategoriesList
}