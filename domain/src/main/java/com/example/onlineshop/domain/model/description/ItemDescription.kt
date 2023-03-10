package com.example.onlineshop.domain.model.description

import com.example.onlineshop.domain.model.ListItem

data class ItemDescription(
    val colors: List<String>,
    val description: String,
    val image_urls: List<String>,
    val name: String,
    val number_of_reviews: Int,
    val price: Int,
    val rating: Double
): ListItem {
    override val id: Long = hashCode().toLong()
}
