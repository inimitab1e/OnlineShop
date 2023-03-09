package com.example.onlineshop.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLoginBinding
import com.example.onlineshop.domain.StringConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoginResponseObserver()
        initClickers()
    }

    private fun initLoginResponseObserver() {
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { loginResponseMessage ->
            if (loginResponseMessage == StringConstants.loginSuccessfulMessage) {
                binding.tvLoginErrorMessage.isGone = true
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            } else {
                with(binding.tvLoginErrorMessage) {
                    isVisible = true
                    text = loginResponseMessage
                }
            }
        }
    }

    private fun initClickers() {
        with(binding) {
            btnLogin.setOnClickListener {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        val firstName = binding.etLogFirstName.text.toString()
        clearErrorMessages()
        loginViewModel.loginWithPassword(firstName)
    }

    private fun clearErrorMessages() {
        binding.tvLoginErrorMessage.isGone = true
    }
}