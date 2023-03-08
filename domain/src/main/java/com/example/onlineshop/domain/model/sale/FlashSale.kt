package com.example.onlineshop.domain.model.sale

import com.example.onlineshop.domain.model.ListItem

data class FlashSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
): ListItem {
    override val id: Long = hashCode().toLong()
}