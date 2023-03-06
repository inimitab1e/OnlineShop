package com.example.onlineshop.domain.network_utils.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
