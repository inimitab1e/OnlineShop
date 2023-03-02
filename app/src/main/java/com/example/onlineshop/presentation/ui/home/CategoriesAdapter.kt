package com.example.onlineshop.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.databinding.CategoryRecyclerviewItemBinding
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.categories.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var categoriesList = mutableListOf<Category>()

    class CategoriesViewHolder(binding: CategoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryName = binding.tvCategoryName
        val categoryImage = binding.ivCategoryImage
    }

    fun setCategoriesItemList(list: CategoriesList) {
        this.categoriesList = list.categories.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            CategoryRecyclerviewItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val itemValues = categoriesList[position]
        holder.categoryName.text = itemValues.categoryName
        holder.categoryImage.setImageResource(chooseCategoryIcon(itemValues.imageUrl))
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
}

enum class CategoriesImageName {
    ic_phones_24,
    ic_headphones_24,
    ic_games_24,
    ic_cars_24,
    ic_furniture_24,
    ic_kids_24
}