package com.example.onlineshop.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.onlineshop.domain.StringConstants


class PreferenceHelper (context: Context) {

    private val name = "OnlineShopPreferences"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun saveEmail(value: String) {
        editor.putString(StringConstants.emailKey, value)
            .apply()
    }

    fun getEmail(): String? = sharedPref.getString(StringConstants.emailKey, null)

    fun clearEmail() {
        editor.clear()
            .apply()
    }
}