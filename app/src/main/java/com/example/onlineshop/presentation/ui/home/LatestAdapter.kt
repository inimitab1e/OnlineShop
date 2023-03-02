package com.example.onlineshop.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.onlineshop.databinding.LatestRecyclerviewItemBinding
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.latest.Latest

class LatestAdapter : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    var categoriesList = mutableListOf<Latest>()

    fun setLatestItemList(list: List<Latest>) {
        categoriesList = list.toMutableList()
        notifyDataSetChanged()
    }

    class LatestViewHolder(binding: LatestRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryName = binding.tvLatestCategory
        val itemName = binding.tvLatestItemName
        val itemPrice = binding.tvLatestItemPrice
        val itemImage = binding.ivLatestItemImage
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LatestViewHolder {
        val binding =
            LatestRecyclerviewItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return LatestViewHolder(binding)
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val value = categoriesList[position]
        holder.categoryName.text = value.category
        holder.itemName.text = value.name
        val priceValue = StringConstants.dollarChar + value.price.toString()
        holder.itemPrice.text = priceValue
        holder.itemImage.load(value.image_url)
    }
}