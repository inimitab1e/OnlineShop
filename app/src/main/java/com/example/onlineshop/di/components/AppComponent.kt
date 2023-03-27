package com.example.onlineshop.di.components

import android.content.Context
import com.example.onlineshop.di.modules.*
import com.example.onlineshop.presentation.ui.home.HomeFragment
import com.example.onlineshop.presentation.ui.home.page2.ItemDetailsFragment
import com.example.onlineshop.presentation.ui.login.LoginFragment
import com.example.onlineshop.presentation.ui.profile.ProfileFragment
import com.example.onlineshop.presentation.ui.registration.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        DispatcherModule::class,
        LocalDataModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    fun injectRegistrationFragment(registrationFragment: RegistrationFragment)
    fun injectProfileFragment(profileFragment: ProfileFragment)
    fun injectLoginFragment(loginFragment: LoginFragment)
    fun injectHomeFragment(homeFragment: HomeFragment)
    fun injectItemDetailsFragment(itemDetailsFragment: ItemDetailsFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder
        fun build(): AppComponent
    }
}