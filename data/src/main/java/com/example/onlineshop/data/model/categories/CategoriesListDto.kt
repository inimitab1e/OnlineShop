package com.example.onlineshop.data.model.categories

import com.google.gson.annotations.SerializedName

data class CategoriesListDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>
)