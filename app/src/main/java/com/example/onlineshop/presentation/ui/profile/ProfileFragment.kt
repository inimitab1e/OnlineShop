package com.example.onlineshop.presentation.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentProfileBinding
import com.example.onlineshop.extensions.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {
        private const val MIMETYPE_IMAGES = "image/*"
    }

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val profileViewModel: ProfileViewModel by viewModels()

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { contentUri ->
            with(binding.ivProfileImage) {
                clear()
                load(contentUri) {
                    listener(
                        onError = { request, throwable ->
                            Toast.makeText(request.context, throwable.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    )
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeInitialUiState()
        initClickers()
    }

    private fun observeInitialUiState() {
        profileViewModel.initialUserInfo.onEach { currentUserInfo ->
            val firstName = currentUserInfo.firstName
            val lastName = currentUserInfo.lastName
            val userName = "$firstName $lastName"
            binding.tvUserName.text = userName
        }.launchWhenStarted(lifecycleScope)
    }

    private fun initClickers() {
        with(binding) {
            btnLogout.setOnClickListener {
                profileViewModel.doLogout()
                findNavController().navigate(R.id.action_profileFragment_to_registrationFragment)
            }

            tvChangePhoto.setOnClickListener {
                pickImage.launch(MIMETYPE_IMAGES)
            }
        }
    }
}