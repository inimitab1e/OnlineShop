package com.example.onlineshop.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.databinding.SearchResponseRecuclerviewItemBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var responseList = mutableListOf<String>()

    inner class SearchViewHolder(binding: SearchResponseRecuclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val searchItemText = binding.tvSearchResponse
    }

    fun setSearchResponseList(list: List<String>) {
        responseList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            SearchResponseRecuclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchViewHolder(binding)
    }

    override fun getItemCount() = responseList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val value = responseList[position]
        holder.searchItemText.text = value
    }
}