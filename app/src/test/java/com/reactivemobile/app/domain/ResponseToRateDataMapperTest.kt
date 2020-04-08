package com.reactivemobile.app.domain

import com.google.gson.Gson
import com.reactivemobile.app.data.model.RatesResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.math.BigDecimal
import java.util.*

@RunWith(JUnit4::class)
class ResponseToRateDataMapperTest {

    private val responseToRateDataMapper = ResponseToRateDataMapper()

    private val gson = Gson()

    @Test
    fun `mapResponseToRateData maps data correctly`() {
        val ratesResponse = gson.fromJson(
            """{"baseCurrency":"EUR","rates":{"AUD":2,"BGN":4}}""",
            RatesResponse::class.java
        )

        val output = responseToRateDataMapper.mapResponseToRateData(ratesResponse, BigDecimal(1))

        assertEquals(
            RateEntry(
                Currency.getInstance("EUR"),
                BigDecimal(1),
                "https://github.com/transferwise/currency-flags/raw/master/src/flags/eur.png"
            ), output.baseCurrency
        )

        assertEquals(
            RateEntry(
                Currency.getInstance("AUD"),
                BigDecimal(2),
                "https://github.com/transferwise/currency-flags/raw/master/src/flags/aud.png"
            ), output.rates[0]
        )
    }
}