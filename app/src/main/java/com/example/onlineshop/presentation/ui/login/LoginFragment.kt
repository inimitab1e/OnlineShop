package com.example.onlineshop.presentation.ui.login

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
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLoginBinding
import com.example.onlineshop.di.DI
import com.example.onlineshop.domain.StringConstants
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        DI.appComponent.injectLoginFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLoginResponseObserver()
        initClickers()
    }

    private fun initLoginResponseObserver() {
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { loginResponseMessage ->
            if (loginResponseMessage == StringConstants.loginSuccessfulMessage) {
                clearErrorMessages()
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
        binding.btnLogin.setOnClickListener {
            doLogin()
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