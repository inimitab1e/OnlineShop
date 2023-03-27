package com.example.onlineshop.presentation.ui.registration

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.MainApp
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentRegistrationBinding
import com.example.onlineshop.di.DI
import com.example.onlineshop.domain.StringConstants
import javax.inject.Inject

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding by viewBinding(FragmentRegistrationBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val registrationViewModel: RegistrationViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        DI.appComponent.injectRegistrationFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
        initValidationErrorsObserver()
        initRegistrationResponseObserver()
    }

    private fun initRegistrationResponseObserver() {
        registrationViewModel.registrationResponse.observe(viewLifecycleOwner) { registrationResponseMessage ->
            if (registrationResponseMessage == StringConstants.registrationSuccessfulMessage) {
                binding.tvEmailErrorMessage.isGone = true
                findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)
            } else {
                with(binding.tvEmailErrorMessage) {
                    text = registrationResponseMessage
                    isVisible = true
                }
            }
        }
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

        clearErrorMessages()
        registrationViewModel.validateAndRegister(firstName, secondName, email)
    }

    private fun clearErrorMessages() {
        with(binding) {
            tvFirstNameErrorMessage.isGone = true
            tvLastNameErrorMessage.isGone = true
            tvEmailErrorMessage.isGone = true
        }
    }

    private fun initValidationErrorsObserver() {

        registrationViewModel.errorValidationFormsMessage.observe(viewLifecycleOwner) { errorMessages ->
            checkNotNull(errorMessages)
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
        }
    }
}