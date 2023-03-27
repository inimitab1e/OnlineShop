package com.example.onlineshop

import android.app.Application
import android.content.Context
import com.example.onlineshop.di.DI
import com.example.onlineshop.di.components.AppComponent
import com.example.onlineshop.di.components.DaggerAppComponent
import timber.log.Timber

class MainApp : Application() {



    override fun onCreate() {
        super.onCreate()

        DI.appComponent = DaggerAppComponent
            .builder()
            .appContext(this)
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    val Context.appComponent: AppComponent
        get() = when (this) {
            is MainApp -> appComponent
            else -> applicationContext.appComponent
        }
}