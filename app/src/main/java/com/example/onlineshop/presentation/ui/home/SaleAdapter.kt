package com.example.onlineshop.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.onlineshop.databinding.SaleRecyclerviewItemBinding
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.sale.FlashSale

class SaleAdapter : RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    var saleList = mutableListOf<FlashSale>()

    class SaleViewHolder(binding: SaleRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val categoryName = binding.tvSaleCategory
        val itemName = binding.tvSaleItemName
        val itemPrice = binding.tvSaleItemPrice
        val itemImage = binding.ivSaleItemImage
        val itemSaleValue = binding.tvSaleValue
    }

    fun setSaleItemList(list: List<FlashSale>) {
        saleList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SaleViewHolder {
        val binding =
            SaleRecyclerviewItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return SaleViewHolder(binding)
    }

    override fun getItemCount() = saleList.size

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        val value = saleList[position]
        holder.categoryName.text = value.category
        holder.itemName.text = value.name
        val priceValue = StringConstants.dollarChar + value.price.toString()
        holder.itemPrice.text = priceValue
        holder.itemImage.load(value.image_url)
        val saleValue = value.discount.toString() + StringConstants.salePercentText
        holder.itemSaleValue.text = saleValue
    }
}