package com.example.onlineshop.data.network

import com.example.onlineshop.data.model.description.ItemDescriptionDto
import com.example.onlineshop.data.model.latest.LatestListDto
import com.example.onlineshop.data.model.sale.SaleListDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getRemoteLatestData(): Response<LatestListDto>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getRemoteSaleData(): Response<SaleListDto>

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getRemoteItemDescriptionData(): Response<ItemDescriptionDto>
}