package com.example.onlineshop.presentation.ui.home.page2

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentItemDetailsBinding
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.domain.model.description.ItemDescription
import com.example.onlineshop.extensions.addToEndPrice
import com.example.onlineshop.extensions.removeFromEndPrice
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment(R.layout.fragment_item_details) {

    private val binding by viewBinding(FragmentItemDetailsBinding::bind)
    private val itemDetailsViewModel: ItemDetailsViewModel by viewModels()

    private val mainImagePagerAdapter: MainImagePagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MainImagePagerAdapter()
    }
    private val secondaryImageRecyclerAdapter: SecondaryImageRecyclerAdapter by lazy(
        LazyThreadSafetyMode.NONE
    ) {
        SecondaryImageRecyclerAdapter()
    }
    private val colorsPickerRecyclerAdapter: ColorsPickerRecyclerAdapter by lazy(
        LazyThreadSafetyMode.NONE
    ) {
        ColorsPickerRecyclerAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initMainImagePager()
        initSecondaryImagesRecyclerView()
        initColorsPickerRecyclerView()
        initClickers()
    }

    private fun initObservers() {
        itemDetailsViewModel.itemDescription.observe(viewLifecycleOwner) { itemDescription ->
            if (itemDescription != null) {
                mainImagePagerAdapter.setImagesToMainPager(itemDescription.image_urls)
                secondaryImageRecyclerAdapter.setImagesToSecondaryPager(itemDescription.image_urls)
                colorsPickerRecyclerAdapter.setColorsList(itemDescription.colors)
                setupUiWithContent(itemDescription)
            }
        }
    }

    private fun initMainImagePager() {

        binding.imageGroup.vpMainImages.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = mainImagePagerAdapter
        }
    }

    private fun initSecondaryImagesRecyclerView() {
        with(binding) {
            rwSecondaryImages.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = secondaryImageRecyclerAdapter
            }

            secondaryImageRecyclerAdapter.setOnItemClickListener(object :
                SecondaryImageRecyclerAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.imageGroup.vpMainImages.currentItem = position
                }
            })
        }
    }

    private fun initColorsPickerRecyclerView() {
        binding.rwColorsList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = colorsPickerRecyclerAdapter
        }

        colorsPickerRecyclerAdapter.setOnItemClickListener(object :
            ColorsPickerRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "Color $position picked", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupUiWithContent(content: ItemDescription) {
        with(binding) {
            tvPickedItemName.text = content.name
            tvPickedItemPrice.text = content.price.toString()
            tvPickedItemDescription.text = content.description
            tvRatingValue.text = content.rating.toString()
            val viewsNumber = StringConstants.openBracket +
                    content.number_of_reviews.toString() +
                    StringConstants.closedBracket
            tvViewsNumber.text = viewsNumber
        }
    }

    private fun initClickers() {
        with(binding) {
            buttonsGroup.ibAddToCart.setOnClickListener {
                val itemPrice = binding.tvPickedItemPrice.text.toString()
                binding.buttonsGroup.btnAddToCartLayout.tvEndPrice.text =
                    binding.buttonsGroup.btnAddToCartLayout.tvEndPrice.text.toString()
                        .addToEndPrice(itemPrice)
            }

            buttonsGroup.ibRemoveFromCart.setOnClickListener {
                setupRemoveButtonUiActivity()
                val itemPrice = binding.tvPickedItemPrice.text.toString()
                binding.buttonsGroup.btnAddToCartLayout.tvEndPrice.text =
                    binding.buttonsGroup.btnAddToCartLayout.tvEndPrice.text.toString()
                        .removeFromEndPrice(itemPrice)
            }

            buttonsGroup.btnAddToCartLayout.btnAddToCart.setOnClickListener {
                Toast.makeText(context, "Items added in cart", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRemoveButtonUiActivity() {
        if (binding.buttonsGroup.btnAddToCartLayout.tvEndPrice.text == StringConstants.minimumEndPrice) {
            binding.buttonsGroup.ibRemoveFromCart.apply {
                isClickable = false
                setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN)
            }
        } else {
            binding.buttonsGroup.ibRemoveFromCart.apply {
                isClickable = true
                colorFilter = null
            }
        }
    }
}