package com.reactivemobile.app.data.remote

import com.reactivemobile.app.data.model.RatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesNetworkService {
    @GET("api/android/latest")
    suspend fun getRates(@Query("base") baseCurrency: String): RatesResponse
}