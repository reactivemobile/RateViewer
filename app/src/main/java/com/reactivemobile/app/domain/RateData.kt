package com.reactivemobile.app.domain

import java.math.BigDecimal
import java.util.*

class RateData(
    val baseCurrency: RateEntry = RateEntry(Currency.getInstance("EUR"), BigDecimal(0.0), null),
    val rates: List<RateEntry> = listOf(),
    val selectedCurrency: Boolean = false,
    val error: Boolean = false
)

class RateEntry(val currency: Currency, val amount: BigDecimal, val path: String?)