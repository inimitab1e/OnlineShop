package com.example.onlineshop.presentation.ui.home

import com.example.onlineshop.domain.model.ListItem
import com.example.onlineshop.presentation.ui.AdapterDelegates.categoryItemDelegate
import com.example.onlineshop.presentation.ui.ContentDiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CategoriesAdapter : AsyncListDifferDelegationAdapter<ListItem>(ContentDiffUtil()) {
    init {
        delegatesManager
            .addDelegate(categoryItemDelegate())
    }
}