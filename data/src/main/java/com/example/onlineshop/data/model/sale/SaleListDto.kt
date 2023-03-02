package com.example.onlineshop.data.model.sale

import com.google.gson.annotations.SerializedName

data class SaleListDto(
    @SerializedName("flash_sale")
    val flash_sale: List<FlashSaleDto>
)