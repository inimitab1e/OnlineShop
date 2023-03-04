package com.example.onlineshop.presentation.ui.home.page2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.onlineshop.databinding.PickedMainImagePagerItemBinding

class MainImagePagerAdapter :  RecyclerView.Adapter<MainImagePagerAdapter.MainImagePagerViewHolder>() {

    var imagesList = mutableListOf<String>()

    inner class MainImagePagerViewHolder(val binding: PickedMainImagePagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivMainImageItem
    }

    fun setImagesToMainPager(list: List<String>) {
        imagesList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainImagePagerViewHolder {
        val binding = PickedMainImagePagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainImagePagerViewHolder(binding)
    }

    override fun getItemCount() = imagesList.size

    override fun onBindViewHolder(holder: MainImagePagerViewHolder, position: Int) {
        val value = imagesList[position]
        holder.image.load(value)
    }
}