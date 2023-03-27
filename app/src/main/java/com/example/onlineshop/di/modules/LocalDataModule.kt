package com.example.onlineshop.di.modules

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.data.local.room.AppDatabase
import com.example.onlineshop.data.local.room.AppDatabaseDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalDataModule {

    @Provides
    @Singleton
    fun provideAppDatabaseDao(db: AppDatabase): AppDatabaseDAO = db.AppDatabaseDAO()

    @Provides
    @Singleton
    fun provideLocalDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "online_shop.db"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}