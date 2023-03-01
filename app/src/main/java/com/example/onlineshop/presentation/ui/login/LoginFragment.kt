package com.example.onlineshop.presentation.ui.login

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
import com.example.onlineshop.databinding.FragmentLoginBinding
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.extensions.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            btnLogin.setOnClickListener {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        val email = binding.etLogEmail.text.toString()
        val password = binding.etLogPassword.text.toString()

        loginViewModel.loginWithPassword(email, password)

        loginViewModel.loginResponse.onEach { loginResponseMessage ->
            if (loginResponseMessage == StringConstants.loginSuccessfulMessage) {
                binding.tvLoginErrorMessage.isGone = true
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            } else {
                with(binding.tvLoginErrorMessage) {
                    isVisible = true
                    text = loginResponseMessage
                }
            }
        }.launchWhenStarted(lifecycleScope)
    }
}