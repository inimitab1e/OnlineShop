package com.example.onlineshop.extensions

import androidx.appcompat.widget.SearchView


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

inline fun SearchView.onTextChange(crossinline listener: (String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            listener(query)
            return true
        }
    })
}