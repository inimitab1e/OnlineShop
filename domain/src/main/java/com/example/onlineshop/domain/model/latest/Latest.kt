package com.example.onlineshop.domain.model.latest

import com.example.onlineshop.domain.model.ListItem

data class Latest(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
): ListItem {
    override val id: Long = hashCode().toLong()
}