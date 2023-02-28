package com.example.onlineshop.presentation.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentRegistrationBinding
import com.example.onlineshop.extensions.launchWhenResumed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
        initValidationErrorsObserver()
    }

    private fun initClickers() {
        with(binding) {
            tvHaveAccLink.setOnClickListener {
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }

            btnCreateAccount.setOnClickListener {
                doBaseValidationAndRegistration()
            }

            tvRegisterWithGoogle.setOnClickListener {
                //TODO GOOGLE REGISTRATION
            }

            tvRegisterWithApple.setOnClickListener {
                //TODO APPLE REGISTRATION
            }
        }
    }

    private fun doBaseValidationAndRegistration() {
        val firstName = binding.etFirstName.text.toString()
        val secondName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        registrationViewModel.validateAndRegister(firstName, secondName, email, password)
    }

    private fun initValidationErrorsObserver() {

        registrationViewModel.errorValidationFormsMessage.observe(viewLifecycleOwner) { errorMessages ->
            if (errorMessages.firstNameError != null) {
                with(binding.tvFirstNameErrorMessage) {
                    isVisible = true
                    text = errorMessages.firstNameError
                }
            } else {
                binding.tvFirstNameErrorMessage.isGone = true
            }

            if (errorMessages.lastNameError != null) {
                with(binding.tvLastNameErrorMessage) {
                    isVisible = true
                    text = errorMessages.lastNameError
                }
            } else {
                binding.tvLastNameErrorMessage.isGone = true
            }

            if (errorMessages.emailError != null) {
                with(binding.tvEmailErrorMessage) {
                    isVisible = true
                    text = errorMessages.emailError
                }
            } else {
                binding.tvEmailErrorMessage.isGone = true
            }

            if (errorMessages.passwordError != null) {
                with(binding.tvPasswordErrorMessage) {
                    isVisible = true
                    text = errorMessages.passwordError
                }
            } else {
                binding.tvPasswordErrorMessage.isGone = true
            }
        }
    }
}