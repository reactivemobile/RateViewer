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

    private val responseString =
        """{"baseCurrency":"EUR","rates":{"AUD":1.598,"BGN":1.979,"BRL":4.194,"CAD":1.516,"CHF":1.15,"CNY":7.762,"CZK":26.141,"DKK":7.599,"GBP":0.885,"HKD":8.982,"HRK":7.515,"HUF":318.914,"IDR":16175.183,"ILS":4.152,"INR":81.748,"ISK":135.29,"JPY":126.587,"KRW":1283.902,"MXN":21.854,"MYR":4.619,"NOK":9.76,"NZD":1.649,"PHP":59.569,"PLN":4.413,"RON":4.821,"RUB":75.203,"SEK":10.674,"SGD":1.541,"THB":35.461,"USD":1.132,"ZAR":15.929}}"""

    private val json by lazy { Gson().fromJson(responseString, RatesResponse::class.java) }

    @Provides
    @Singleton
    fun provideRetrofit() = object : RatesNetworkService {
        override suspend fun getRates(baseCurrency: String) = json
    }
}