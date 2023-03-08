package com.example.onlineshop.domain.model.categories

import com.example.onlineshop.domain.model.ListItem

data class Category(
    val categoryName: String,
    val imageUrl: String
):ListItem {
    override val id: Long = hashCode().toLong()
}