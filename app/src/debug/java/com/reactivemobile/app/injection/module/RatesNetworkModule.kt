package com.reactivemobile.app.injection.module

import com.reactivemobile.app.data.remote.RatesNetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RatesNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RatesNetworkService {
        val baseUrl = "https://hiring.revolut.codes"

        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RatesNetworkService::class.java)
    }
}