package com.reactivemobile.app.domain

import java.math.BigDecimal

interface FetchRatesUseCase {
    suspend fun getRates(baseCurrency: String): RateData

    fun setBaseAmount(amount: BigDecimal): RateData
}