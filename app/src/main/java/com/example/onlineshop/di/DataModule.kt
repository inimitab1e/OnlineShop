package com.example.onlineshop.di

import android.content.Context
import com.example.onlineshop.data.local.AppDatabase
import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.data.repositories.AuthenticationRepositoryImpl
import com.example.onlineshop.data.repositories.ValidationRepositoryImpl
import com.example.onlineshop.domain.local.AppDatabaseDAO
import com.example.onlineshop.domain.repositories.AuthenticationRepository
import com.example.onlineshop.domain.repositories.ValidationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        appDatabase: AppDatabaseDAO,
        ioDispatcher: CoroutineDispatcher,
        preferenceHelper: PreferenceHelper
    ): AuthenticationRepository =
        AuthenticationRepositoryImpl(
            appDatabase = appDatabase,
            ioDispatcher = ioDispatcher,
            preferenceHelper = preferenceHelper
        )

    @Provides
    fun provideAppDatabaseDao(db: AppDatabase): AppDatabaseDAO = db.AppDatabaseDAO()

    @Provides
    @Singleton
    fun provideValidationRepository(): ValidationRepository = ValidationRepositoryImpl()

    @Provides
    @Singleton
    fun providePrefHelper(@ApplicationContext context: Context): PreferenceHelper =
        PreferenceHelper(context = context)
}