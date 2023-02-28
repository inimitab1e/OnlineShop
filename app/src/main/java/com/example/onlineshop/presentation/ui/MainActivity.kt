package com.example.onlineshop.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navConrtoller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()
        supportActionBar?.hide()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navConrtoller = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navConrtoller)

        //list of fragment id's, where bottomNav must not be visible
        val onInvisibleBottomNavFragments =
            listOf(R.id.loginFragment, R.id.registrationFragment)

        navConrtoller.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in onInvisibleBottomNavFragments) {
                binding.bottomNavigationView.isGone = true
            } else {
                binding.bottomNavigationView.isVisible = true
            }
        }
    }
}