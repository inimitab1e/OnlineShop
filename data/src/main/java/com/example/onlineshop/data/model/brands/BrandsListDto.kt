package com.example.onlineshop.data.model.brands

import com.google.gson.annotations.SerializedName

data class BrandsListDto(
    @SerializedName("brands")
    val brands: List<BrandDto>
)