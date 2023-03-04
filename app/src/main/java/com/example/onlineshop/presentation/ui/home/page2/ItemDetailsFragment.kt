package com.example.onlineshop.presentation.ui.home.page2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentItemDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment(R.layout.fragment_item_details) {

    private val binding by viewBinding(FragmentItemDetailsBinding::bind)
    private val itemDetailsViewModel: ItemDetailsViewModel by viewModels()

    private val mainImagePagerAdapter: MainImagePagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainImagePagerAdapter()
    }
    private val secondaryImagePagerAdapter: SecondaryImagePagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SecondaryImagePagerAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initMainImagePager()
        initSecondaryImagesRecyclerView()
    }

    private fun initObservers() {
        itemDetailsViewModel.itemDescription.observe(viewLifecycleOwner) { itemDescription ->
            if (itemDescription != null) {
                mainImagePagerAdapter.setImagesToMainPager(itemDescription.image_urls)
                secondaryImagePagerAdapter.setImagesToSecondaryPager(itemDescription.image_urls)
            }
        }
    }

    private fun initMainImagePager() {
        binding.vpMainImages.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mainImagePagerAdapter
        }
    }

    private fun initSecondaryImagesRecyclerView() {
        with(binding) {
            rwSecondaryImages.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = secondaryImagePagerAdapter
            }

            secondaryImagePagerAdapter.setOnItemClickListener(object :
                SecondaryImagePagerAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.vpMainImages.currentItem = position
                }
            })
        }
    }
}