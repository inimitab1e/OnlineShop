package com.example.onlineshop.di.modules

import com.example.onlineshop.data.repositories.*
import com.example.onlineshop.domain.repositories.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthenticationRepository(
        authenticationRepository: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    abstract fun bindHomePageRepository(
        homePageRepositoryImpl: HomePageRepositoryImpl
    ): HomePageRepository

    @Binds
    abstract fun bindItemDescriptionRepository(
        itemDescriptionRepositoryImpl: ItemDescriptionRepositoryImpl
    ): ItemDescriptionRepository

    @Binds
    abstract fun bindValidationRepository(
        validationRepositoryImpl: ValidationRepositoryImpl
    ): ValidationRepository
}