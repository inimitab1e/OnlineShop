package com.example.onlineshop.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.di.ViewModelKey
import com.example.onlineshop.presentation.ui.ViewModelFactory
import com.example.onlineshop.presentation.ui.home.HomeViewModel
import com.example.onlineshop.presentation.ui.home.page2.ItemDetailsViewModel
import com.example.onlineshop.presentation.ui.login.LoginViewModel
import com.example.onlineshop.presentation.ui.profile.ProfileViewModel
import com.example.onlineshop.presentation.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    internal abstract fun providesRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun providesProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun providesHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailsViewModel::class)
    internal abstract fun providesItemDetailsViewModel(viewModel: ItemDetailsViewModel): ViewModel
}