package com.example.onlineshop.data.model.latest

import com.google.gson.annotations.SerializedName

data class LatestDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)