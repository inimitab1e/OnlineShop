package com.example.onlineshop.domain.repositories

import com.example.onlineshop.domain.model.description.ItemDescription

interface ItemDescriptionRepository {

    suspend fun getItemDescription(): ItemDescription?
}