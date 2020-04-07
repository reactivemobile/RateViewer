package com.reactivemobile.app.injection.module

import com.reactivemobile.app.data.remote.RatesNetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RatesNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RatesNetworkService {
        val baseUrl = "https://hiring.revolut.codes"

        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RatesNetworkService::class.java)
    }
}