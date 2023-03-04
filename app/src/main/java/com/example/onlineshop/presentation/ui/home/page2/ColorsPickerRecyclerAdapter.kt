package com.example.onlineshop.presentation.ui.home.page2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.databinding.PickedColorsListItemBinding
import com.example.onlineshop.domain.StringConstants

class ColorsPickerRecyclerAdapter :
    RecyclerView.Adapter<ColorsPickerRecyclerAdapter.ColorsPickerViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private var colorsList: List<String> = mutableListOf()

    inner class ColorsPickerViewHolder(
        binding: PickedColorsListItemBinding,
        clickListener: ColorsPickerRecyclerAdapter.OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val item = binding.colorsItemLayout
        val itemImage = binding.ivColorsItem

        init {
            item.setOnClickListener {
                clickListener.onItemClick(position = absoluteAdapterPosition)
            }
        }
    }

    fun setColorsList(list: List<String>) {
        colorsList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsPickerViewHolder {
        val binding = PickedColorsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ColorsPickerViewHolder(binding, mListener)
    }

    override fun getItemCount() = colorsList.size

    override fun onBindViewHolder(holder: ColorsPickerViewHolder, position: Int) {
        val value = colorsList[position]
        val colorId = Color.parseColor(value)
        if (value == StringConstants.colorWhite) {
            holder.itemImage.setImageResource(R.drawable.color_item_picked_stroke)
        }
        holder.itemImage.setBackgroundColor(colorId)
    }
}