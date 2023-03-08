package com.example.onlineshop.data

import com.example.onlineshop.data.model.brands.BrandDto
import com.example.onlineshop.data.model.brands.BrandsListDto
import com.example.onlineshop.data.model.categories.CategoriesListDto
import com.example.onlineshop.data.model.categories.CategoryDto
import com.example.onlineshop.data.model.description.ItemDescriptionDto
import com.example.onlineshop.data.model.latest.LatestDto
import com.example.onlineshop.data.model.latest.LatestListDto
import com.example.onlineshop.data.model.sale.FlashSaleDto
import com.example.onlineshop.data.model.sale.SaleListDto
import com.example.onlineshop.data.model.search.SearchResponseDto
import com.example.onlineshop.domain.local.entities.Users
import com.example.onlineshop.domain.model.CurrentUserInfo
import com.example.onlineshop.domain.model.brands.Brand
import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.categories.Category
import com.example.onlineshop.domain.model.description.ItemDescription
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.latest.LatestList
import com.example.onlineshop.domain.model.sale.FlashSale
import com.example.onlineshop.domain.model.sale.SaleList
import com.example.onlineshop.domain.model.search.SearchResponse

fun Users.toCurrentUserInfo(): CurrentUserInfo = CurrentUserInfo(
    firstName = firstName ?: "first name",
    lastName = lastName ?: "last name"
)

fun CategoriesListDto.toCategoriesList(): CategoriesList = CategoriesList(
    categories = categories.map { item -> item.toCategory() }
)

fun CategoryDto.toCategory(): Category = Category(
    categoryName = categoryName,
    imageUrl = imageUrl
)

fun LatestDto.toLatest(): Latest = Latest(
    category = category,
    image_url = image_url,
    name = name,
    price = price
)

fun FlashSaleDto.toFlashSale(): FlashSale = FlashSale(
    category = category,
    discount = discount,
    image_url = image_url,
    name = name,
    price = price
)

fun BrandsListDto.toBrandsList(): BrandsList = BrandsList(
    brands = brands.map { value -> value.toBrand() }
)

fun BrandDto.toBrand(): Brand = Brand(
    brandName = brandName
)

fun ItemDescriptionDto.toItemDescription(): ItemDescription = ItemDescription(
    colors = colors,
    description = description,
    image_urls = image_urls,
    name = name,
    number_of_reviews = number_of_reviews,
    price = price,
    rating = rating
)