package com.example.onlineshop.di.modules

import android.content.Context
import androidx.room.Room
import com.example.onlineshop.data.local.PreferenceHelper
import com.example.onlineshop.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providePrefHelper(context: Context): PreferenceHelper =
        PreferenceHelper(context)
}