package com.example.onlineshop.domain.model.brands

import com.example.onlineshop.domain.model.ListItem

data class Brand(
    val brandName: String
): ListItem {
    override val id: Long = hashCode().toLong()
}