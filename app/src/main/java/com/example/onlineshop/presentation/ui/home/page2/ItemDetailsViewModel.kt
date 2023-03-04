package com.example.onlineshop.presentation.ui.home.page2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.onlineshop.domain.model.description.ItemDescription
import com.example.onlineshop.domain.repositories.ItemDescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val itemDescriptionRepository: ItemDescriptionRepository
) : ViewModel() {

    val itemDescription: LiveData<ItemDescription?> = liveData {
        emit(itemDescriptionRepository.getItemDescription())
    }
}