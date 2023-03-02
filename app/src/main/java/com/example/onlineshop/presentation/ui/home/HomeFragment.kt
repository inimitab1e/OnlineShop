package com.example.onlineshop.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.domain.model.latest.Latest
import com.example.onlineshop.domain.model.sale.FlashSale
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel: HomeViewModel by viewModels()

    private val categoriesAdapter: CategoriesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoriesAdapter()
    }
    private val latestAdapter: LatestAdapter by lazy(LazyThreadSafetyMode.NONE) {
        LatestAdapter()
    }
    private val saleAdapter: SaleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SaleAdapter()
    }
    private val brandsAdapter: BrandsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BrandsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()
        setupCategoriesRecyclerViewContent()
        setupLatestAndSaleRecyclerViewContent()
        setupBrandsRecyclerViewContent()
        setupErrorsObserver()
    }

    private fun initRecyclerViews() {
        binding.rwCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

        binding.rwLatest.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = latestAdapter
        }

        binding.rwFlashSale.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = saleAdapter
        }

        binding.rwBrands.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = brandsAdapter
        }
    }

    private fun setupCategoriesRecyclerViewContent() {
        homeViewModel.categoriesList.observe(viewLifecycleOwner) { categoriesList ->
            if (categoriesList != null) {
                categoriesAdapter.setCategoriesItemList(categoriesList)
            }
        }
    }

    private fun setupLatestAndSaleRecyclerViewContent() {
        homeViewModel.latestList.observe(viewLifecycleOwner) { latestList ->
            if (latestList != null) {
                latestAdapter.setLatestItemList(latestList)
            }
        }

        homeViewModel.saleList.observe(viewLifecycleOwner) { saleList ->
            if (saleList != null) {
                saleAdapter.setSaleItemList(saleList)
            }
        }
    }

    private fun setupBrandsRecyclerViewContent() {
        homeViewModel.brandsList.observe(viewLifecycleOwner) { brandsList ->
            if (brandsList != null) {
                brandsAdapter.setBrandsItemList(brandsList)
            }
        }
    }

    private fun setupErrorsObserver() {
        homeViewModel.loadDataErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}