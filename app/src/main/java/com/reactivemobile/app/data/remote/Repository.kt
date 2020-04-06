package com.reactivemobile.app.data.remote

import javax.inject.Inject

class Repository @Inject constructor(private val service: RatesNetworkService) {
    suspend fun getRates(baseCurrency: String) = service.getRates(baseCurrency)
}
