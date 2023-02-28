package com.example.onlineshop.domain.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) var ID: Int? = null,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    var password: String?
)