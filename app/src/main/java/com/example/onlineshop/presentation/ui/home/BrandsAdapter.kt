package com.example.onlineshop.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.databinding.BrandsRecyclerviewItemBinding
import com.example.onlineshop.domain.model.brands.Brand
import com.example.onlineshop.domain.model.brands.BrandsList

class BrandsAdapter : RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {

    var brandsList = mutableListOf<Brand>()

    class BrandsViewHolder(binding: BrandsRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val brandName = binding.tvBrandName
    }

    fun setBrandsItemList(list: BrandsList) {
        brandsList = list.brands.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BrandsViewHolder {
        val binding =
            BrandsRecyclerviewItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return BrandsViewHolder(binding)
    }

    override fun getItemCount() = brandsList.size

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val itemValues = brandsList[position]
        holder.brandName.text = itemValues.brandName
    }
}