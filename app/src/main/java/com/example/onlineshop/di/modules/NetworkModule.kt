package com.example.onlineshop.di.modules

import com.example.onlineshop.data.network.ApiService
import com.example.onlineshop.domain.StringConstants
import com.example.onlineshop.data.network.utils.retrofit.ResultAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(mOkHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(StringConstants.baseUrl)
            .client(mOkHttpClient)
            .addCallAdapterFactory(ResultAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAuthService(client: Retrofit): ApiService = client.create(ApiService::class.java)
}