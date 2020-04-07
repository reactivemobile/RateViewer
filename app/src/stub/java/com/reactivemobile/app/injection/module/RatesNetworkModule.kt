package com.reactivemobile.app.injection.module

import com.google.gson.Gson
import com.reactivemobile.app.data.model.RatesResponse
import com.reactivemobile.app.data.remote.RatesNetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A network module providing fake response data
 */
@Module
class RatesNetworkModule {

    private val responseStringEur =
        """{"baseCurrency":"EUR","rates":{"AUD":2,"BGN":4}}"""
    private val responseStringAud =
        """{"baseCurrency":"AUD","rates":{"BGN":2, EUR:0.5}}"""
    private val responseStringBgn =
        """{"baseCurrency":"BGN","rates":{"AUD":0.5,"EUR":0.25}}"""

    private val responseMap = mapOf<String, String>(
        "EUR" to responseStringEur,
        "AUD" to responseStringAud,
        "BGN" to responseStringBgn
    )

    private val gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit() = object : RatesNetworkService {
        override suspend fun getRates(baseCurrency: String) =
            gson.fromJson(responseMap[baseCurrency], RatesResponse::class.java)
    }
}