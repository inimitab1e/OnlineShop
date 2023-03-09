package com.example.onlineshop.di

import android.content.Context
import com.example.onlineshop.data.local.AppDatabase
import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.data.local.AppDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideAppDatabaseDao(db: AppDatabase): AppDatabaseDAO = db.AppDatabaseDAO()

    @Provides
    @Singleton
    fun providePrefHelper(@ApplicationContext context: Context): PreferenceHelper =
        PreferenceHelper(context)
}