package com.example.onlineshop.extensions


fun String.addToEndPrice(itemPrice: String): String {
    return (this.toDouble() + itemPrice.toDouble()).toString()
}

fun String.removeFromEndPrice(itemPrice: String): String {
    return if (this.toDouble() - itemPrice.toDouble() >= 0) {
        (this.toDouble() - itemPrice.toDouble()).toString()
    } else {
        this
    }
}