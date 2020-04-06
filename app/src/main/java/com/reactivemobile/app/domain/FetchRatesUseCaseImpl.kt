package com.reactivemobile.app.domain

import java.math.BigDecimal
import com.reactivemobile.app.data.model.RatesResponse
import com.reactivemobile.app.data.remote.Repository

class FetchRatesUseCaseImpl(
    private val repository: Repository,
    private val responseToRateDataMapper: ResponseToRateDataMapper,
    private var baseAmount: BigDecimal

) : FetchRatesUseCase {

    private lateinit var latestResponse: RatesResponse

    override suspend fun getRates(baseCurrency: String): RateData {
        return try {
            latestResponse = repository.getRates(baseCurrency)
            responseToRateDataMapper.mapResponseToRateData(latestResponse, baseAmount)
        } catch (e: Exception) {
            RateData(error = true)
        }
    }

    override fun setBaseAmount(amount: BigDecimal): RateData {
        if (::latestResponse.isInitialized) {
            baseAmount = amount
            return responseToRateDataMapper.mapResponseToRateData(latestResponse, baseAmount)
        }
        return RateData(error = true)
    }
}