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
        """{"baseCurrency":"EUR","rates":{"AUD":1.598,"BGN":1.979}}"""
    private val responseStringAud =
        """{"baseCurrency":"AUD","rates":{"BGN":1.444, EUR:0.678}}"""
    private val responseStringBgn =
        """{"baseCurrency":"BGN","rates":{"AUD":4.554,"EUR":8.999}}"""

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