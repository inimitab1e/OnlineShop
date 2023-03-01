package com.example.onlineshop.domain

import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.model.CurrentUserInfo

fun Users.toCurrentUserInfo(): CurrentUserInfo = CurrentUserInfo(
    firstName = firstName ?: "first name",
    lastName = lastName ?: "last name"
)