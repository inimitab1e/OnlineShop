package com.example.onlineshop.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    @SerializedName("words")
    val words: List<String>
)