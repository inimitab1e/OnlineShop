package com.example.onlineshop.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "online_shop.db"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}