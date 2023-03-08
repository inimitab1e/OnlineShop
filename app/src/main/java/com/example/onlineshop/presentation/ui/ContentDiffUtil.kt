package com.example.onlineshop.presentation.ui

import com.example.onlineshop.domain.model.ListItem


class ContentDiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ListItem>() {
  override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.equals(newItem)
  }
}