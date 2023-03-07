package com.example.onlineshop.presentation.ui.home

import androidx.lifecycle.*
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.brands.BrandsList
import com.example.onlineshop.domain.model.categories.CategoriesList
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale
import com.example.onlineshop.domain.model.search.SearchResponse
import com.example.onlineshop.domain.repositories.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homePageRepository: HomePageRepository
) : ViewModel() {

    private var latestResponse: List<Latest>? = null
    private var saleResponse: List<FlashSale>? = null
    private var searchJob: Job? = null

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

    private val _searchResponseList = MutableLiveData<SearchResponse?>()
    val searchResponseList: LiveData<SearchResponse?> get() = _searchResponseList

    init {
        viewModelScope.launch {
            val latestAndSaleJobs = listOf(
                viewModelScope.async {
                    latestResponse = homePageRepository.getLatestList()
                    delay(5000)
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

    fun doSearchByQuery(query: String) {
        stopSearching()
        searchJob = viewModelScope.launch {
            delay(searchDelay)
            _searchResponseList.postValue(homePageRepository.doSearch(query))
        }
    }

    fun stopSearching() {
        if (searchJob?.isActive == true) {
            searchJob?.cancel()
        }
    }

    companion object {
        private const val searchDelay = 1000L
    }
}