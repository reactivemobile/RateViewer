package com.reactivemobile.app.domain

import java.math.BigDecimal
import java.util.*

data class RateData(
    val baseCurrency: RateEntry = RateEntry(Currency.getInstance("EUR"), BigDecimal(0.0), null),
    val rates: List<RateEntry> = listOf(),
    val error: Boolean = false
)

data class RateEntry(val currency: Currency, val amount: BigDecimal, val path: String?)