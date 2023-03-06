package com.example.onlineshop.domain.network_utils.result

class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val url: String? = null,
    cause: Throwable? = null
) : Exception(null, cause)
