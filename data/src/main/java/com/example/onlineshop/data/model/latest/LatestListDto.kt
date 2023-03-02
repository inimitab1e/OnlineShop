package com.example.onlineshop.data.model.latest

import com.google.gson.annotations.SerializedName

data class LatestListDto(
    @SerializedName("latest")
    val latest: List<LatestDto>
)