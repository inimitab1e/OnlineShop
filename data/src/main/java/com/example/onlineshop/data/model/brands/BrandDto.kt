package com.example.onlineshop.data.model.brands

import com.google.gson.annotations.SerializedName

data class BrandDto(
    @SerializedName("brandName")
    val brandName: String
)