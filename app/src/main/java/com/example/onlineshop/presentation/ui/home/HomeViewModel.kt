package com.example.onlineshop.presentation.ui.home

import androidx.lifecycle.*
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale
import com.example.onlineshop.domain.repositories.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePageRepository: HomePageRepository
) : ViewModel() {

    private var latestResponse: List<Latest>? = null
    private var saleResponse: List<FlashSale>? = null

    val categoriesList: LiveData<CategoriesList?> = liveData {
        emit(homePageRepository.getCategoriesList())
    }

    val brandsList: LiveData<BrandsList?> = liveData {
        emit(homePageRepository.getBrandsList())
    }

    private val _loadDataErrorMessage = MutableLiveData<String?>(null)
    val loadDataErrorMessage: LiveData<String?> get() = _loadDataErrorMessage

    private val _latestList = MutableLiveData<List<Latest>?>()
    val latestList: LiveData<List<Latest>?> get() = _latestList

    private val _saleList = MutableLiveData<List<FlashSale>?>()
    val saleList: LiveData<List<FlashSale>?> get() = _saleList

    private val _isDataReceivedSuccessfully = MutableLiveData(false)
    val isDataReceivedSuccessfully: LiveData<Boolean?> get() = _isDataReceivedSuccessfully

    init {
        viewModelScope.launch {
            val latestAndSaleJobs = listOf(
                viewModelScope.async {
                    latestResponse = homePageRepository.getLatestList()
                },
                viewModelScope.async {
                    saleResponse = homePageRepository.getSaleList()
                }
            )
            latestAndSaleJobs.awaitAll()
            if (latestResponse == null || saleResponse == null) {
                _loadDataErrorMessage.postValue(StringConstants.dataReceiveErrorMessage)
            } else {
                _latestList.postValue(latestResponse)
                _saleList.postValue(saleResponse)
                _isDataReceivedSuccessfully.postValue(true)
            }
        }
    }
}