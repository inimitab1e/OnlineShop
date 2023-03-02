package com.example.onlineshop.data.model.categories

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)