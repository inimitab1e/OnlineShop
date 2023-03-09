package com.example.onlineshop.data.network.utils.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
