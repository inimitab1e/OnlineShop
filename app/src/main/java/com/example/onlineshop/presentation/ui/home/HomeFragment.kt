package com.example.onlineshop.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.di.DI
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.extensions.onTextChange
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        DI.appComponent.injectHomeFragment(this)
        super.onAttach(context)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        CategoriesAdapter()
    }
    private val latestAdapter: LatestAdapter by lazy(LazyThreadSafetyMode.NONE) {
        LatestAdapter()
    }
    private val saleAdapter: SaleAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SaleAdapter {
            findNavController().navigate(R.id.action_homeFragment_to_itemDetailsFragment)
        }
    }
    private val brandsAdapter: BrandsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BrandsAdapter()
    }
    private val searchAdapter: SearchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()
        setupRecyclerViewsWithContent()
        setupSearchRecyclerViewContent()
        setupErrorsObserver()
        setupUiStateObserver()
        initBackButtonPress()
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

        binding.rwSearchResponseList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter
        }
    }

    private fun setupRecyclerViewsWithContent() {
        homeViewModel.categoriesList.observe(viewLifecycleOwner) { categoriesList ->
            if (categoriesList != null) {
                categoriesAdapter.items = categoriesList.categories
            }
        }

        homeViewModel.latestList.observe(viewLifecycleOwner) { latestList ->
            if (latestList != null) {
                latestAdapter.items = latestList
            }
        }

        homeViewModel.saleList.observe(viewLifecycleOwner) { saleList ->
            if (saleList != null) {
                saleAdapter.items = saleList
            }
        }

        homeViewModel.brandsList.observe(viewLifecycleOwner) { brandsList ->
            if (brandsList != null) {
                brandsAdapter.items = brandsList[0].brands
            }
        }
    }

    private fun setupSearchRecyclerViewContent() {
        binding.searchBar.onTextChange { query ->
            if (query != null && query.isNotBlank()) {
                homeViewModel.doSearchByQuery(query)
            } else {
                homeViewModel.stopSearching()
                binding.rwSearchResponseList.isGone = true
                binding.mainContent.isVisible = true
            }
        }

        homeViewModel.searchResponseList.observe(viewLifecycleOwner) { searchResponse ->
            if (searchResponse != null) {
                if (searchResponse.searchResult.isEmpty()) {
                    showToast(StringConstants.cantFindDataMessage)
                } else {
                    binding.rwSearchResponseList.isVisible = true
                    binding.mainContent.isGone = true
                    searchAdapter.setSearchResponseList(searchResponse.searchResult)
                }
            }
        }
    }

    private fun setupErrorsObserver() {
        homeViewModel.loadDataErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                showToast(errorMessage)
            }
        }
    }

    private fun setupUiStateObserver() {
        homeViewModel.isDataReceivedSuccessfully.observe(viewLifecycleOwner) { isReceived ->
            if (isReceived != null && isReceived) {
                with(binding) {
                    rwLatest.isVisible = true
                    rwFlashSale.isVisible = true
                    rwBrands.isVisible = true

                    shimmerLatest.isGone = true
                    shimmerSale.isGone = true
                    shimmerBrands.isGone = true
                }
                startShimmerAnimation()
            } else {
                with(binding) {
                    rwLatest.isGone = true
                    rwFlashSale.isGone = true
                    rwBrands.isGone = true

                    shimmerLatest.isVisible = true
                    shimmerSale.isVisible = true
                    shimmerBrands.isVisible = true
                }
                stopShimmerAnimation()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        startShimmerAnimation()
    }

    private fun startShimmerAnimation() {
        with(binding) {
            shimmerLatest.startShimmerAnimation()
            shimmerSale.startShimmerAnimation()
            shimmerBrands.startShimmerAnimation()
        }
    }

    override fun onPause() {
        super.onPause()
        stopShimmerAnimation()
    }

    private fun stopShimmerAnimation() {
        with(binding) {
            shimmerLatest.stopShimmerAnimation()
            shimmerSale.stopShimmerAnimation()
            shimmerBrands.stopShimmerAnimation()
        }
    }

    private fun initBackButtonPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
            }
            activity?.finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}