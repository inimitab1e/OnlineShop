package com.example.onlineshop.presentation.ui.home

import androidx.lifecycle.*
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.repositories.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePageRepository: HomePageRepository
) : ViewModel() {

    val categoriesList: LiveData<CategoriesList?> = liveData {
        emit(homePageRepository.getCategoriesList())
    }
}