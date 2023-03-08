package com.example.onlineshop.presentation.ui.home

import com.example.onlineshop.domain.model.ListItem
import com.example.onlineshop.presentation.ui.AdapterDelegates.latestItemDelegate
import com.example.onlineshop.presentation.ui.ContentDiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class LatestAdapter : AsyncListDifferDelegationAdapter<ListItem>(ContentDiffUtil()) {
    init {
        delegatesManager.addDelegate(latestItemDelegate())
    }
}