package com.reactivemobile.app.domain

import com.reactivemobile.app.data.model.RatesResponse
import java.math.BigDecimal
import java.util.*

class ResponseToRateDataMapper {
    fun mapResponseToRateData(response: RatesResponse, baseAmount: BigDecimal): RateData {

        val entries: MutableList<RateEntry> = mutableListOf()
        val baseCurrency = RateEntry(
            Currency.getInstance(response.baseCurrency),
            baseAmount,
            getPath(response.baseCurrency)
        )

        response.rates.keys.forEach {
            val path = getPath(it)
            val rateValue = response.rates.getOrDefault(it, BigDecimal.valueOf(-1))  * baseAmount
            val rateEntry = RateEntry(Currency.getInstance(it), rateValue, path)
            entries.add(rateEntry)
        }

        return RateData(baseCurrency, entries)
    }

    private fun getPath(it: String?): String {
        return "https://github.com/transferwise/currency-flags/raw/master/src/flags/$it.png".toLowerCase(
            Locale.ENGLISH
        )
    }
}