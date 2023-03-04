package com.example.onlineshop.presentation.ui.home.page2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.onlineshop.databinding.PickedSecondaryImagePagerItemBinding


class SecondaryImagePagerAdapter :
    RecyclerView.Adapter<SecondaryImagePagerAdapter.SecondaryImagePagerViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private var imagesList = mutableListOf<String>()

    inner class SecondaryImagePagerViewHolder(
        val binding: PickedSecondaryImagePagerItemBinding,
        clickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val item = binding.secondaryImageItemLayout
        val image = binding.ivSecondaryImageItem

        init {
            item.setOnClickListener {
                clickListener.onItemClick(position = absoluteAdapterPosition)
            }
        }
    }

    fun setImagesToSecondaryPager(list: List<String>) {
        imagesList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SecondaryImagePagerViewHolder {
        val binding = PickedSecondaryImagePagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SecondaryImagePagerViewHolder(binding, mListener)
    }

    override fun getItemCount() = imagesList.size

    override fun onBindViewHolder(holder: SecondaryImagePagerViewHolder, position: Int) {
        val value = imagesList[position]
        holder.image.load(value)
    }
}