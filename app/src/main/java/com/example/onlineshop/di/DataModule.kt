package com.example.onlineshop.di

import com.example.onlineshop.data.repositories.ValidationRepositoryImpl
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideValidationRepository(): ValidationRepository = ValidationRepositoryImpl()
}