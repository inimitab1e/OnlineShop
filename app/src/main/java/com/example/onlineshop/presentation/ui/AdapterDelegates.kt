package com.example.onlineshop.presentation.ui

import coil.load
import com.example.onlineshop.R
import com.example.onlineshop.databinding.*
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.ListItem
import com.example.onlineshop.domain.model.brands.Brand
import com.example.onlineshop.domain.model.categories.Category
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AdapterDelegates {

    fun categoryItemDelegate() =
        adapterDelegateViewBinding<Category, ListItem, CategoryRecyclerviewItemBinding>({ inflater, container ->
            CategoryRecyclerviewItemBinding.inflate(inflater, container, false)
        }
        ) {
            bind {
                with(binding) {
                    tvCategoryName.text = item.categoryName
                    ivCategoryImage.setImageResource(chooseCategoryIcon(item.imageUrl))
                }
            }
        }

    private fun chooseCategoryIcon(category: String): Int {
        return when (category) {
            CategoriesImageName.ic_phones_24.toString() -> R.drawable.ic_phones_24
            CategoriesImageName.ic_headphones_24.toString() -> R.drawable.ic_headphones_24
            CategoriesImageName.ic_games_24.toString() -> R.drawable.ic_games_24
            CategoriesImageName.ic_cars_24.toString() -> R.drawable.ic_cars_24
            CategoriesImageName.ic_furniture_24.toString() -> R.drawable.ic_furniture_24
            else -> R.drawable.ic_kids_24
        }
    }

    fun latestItemDelegate() =
        adapterDelegateViewBinding<Latest, ListItem, LatestRecyclerviewItemBinding>({ inflater, container ->
            LatestRecyclerviewItemBinding.inflate(inflater, container, false)
        }
        ) {
            bind {
                with(binding) {
                    tvLatestCategory.text = item.category
                    tvLatestItemName.text = item.name
                    tvLatestItemPrice.text = StringConstants.dollarChar + item.price.toString()
                    ivLatestItemImage.load(item.image_url)
                }
            }
        }

    fun flashSaleItemDelegate(clickListener: () -> Unit) =
        adapterDelegateViewBinding<FlashSale, ListItem, SaleRecyclerviewItemBinding>({ inflater, container ->
            SaleRecyclerviewItemBinding.inflate(inflater, container, false)
        }
        ) {
            binding.root.setOnClickListener {
                clickListener()
            }
            bind {
                with(binding) {
                    tvSaleItemName.text = item.name
                    tvSaleCategory.text = item.category
                    tvSaleItemPrice.text = StringConstants.dollarChar + item.price.toString()
                    tvSaleValue.text = item.discount.toString() + StringConstants.salePercentText
                    ivSaleItemImage.load(item.image_url)
                }
            }
        }

    fun brandsItemDelegate() =
        adapterDelegateViewBinding<Brand, ListItem, BrandsRecyclerviewItemBinding>({ inflater, container ->
            BrandsRecyclerviewItemBinding.inflate(inflater, container, false)
        }
        ) {
            bind {
                with(binding) {
                    tvBrandName.text = item.brandName
                }
            }
        }
}

enum class CategoriesImageName {
    ic_phones_24,
    ic_headphones_24,
    ic_games_24,
    ic_cars_24,
    ic_furniture_24,
    ic_kids_24
}