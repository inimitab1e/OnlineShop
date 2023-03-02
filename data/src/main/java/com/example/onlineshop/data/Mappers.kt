package com.example.onlineshop.data

import com.example.onlineshop.data.model.categories.CategoriesListDto
import com.example.onlineshop.data.model.categories.CategoryDto
import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.categories.Category

fun Users.toCurrentUserInfo(): CurrentUserInfo = CurrentUserInfo(
    firstName = firstName ?: "first name",
    lastName = lastName ?: "last name"
)

fun CategoriesListDto.toCategoriesList() : CategoriesList = CategoriesList(
    categories = categories.map { item -> item.toCategory() }
)

fun CategoryDto.toCategory() : Category = Category(
    categoryName = categoryName,
    imageUrl = imageUrl
)