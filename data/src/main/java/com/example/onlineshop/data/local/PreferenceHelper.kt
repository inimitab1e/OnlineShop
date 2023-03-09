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

    fun saveFirstName(value: String) {
        editor.putString(StringConstants.firstNameKey, value)
            .apply()
    }

    fun getFirstName(): String? = sharedPref.getString(StringConstants.firstNameKey, null)

    fun clearFirstName() {
        editor.clear()
            .apply()
    }
}