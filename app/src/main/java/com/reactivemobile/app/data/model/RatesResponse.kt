package com.reactivemobile.app.data.model

import java.math.BigDecimal
import java.util.*

data class RatesResponse(val baseCurrency: String, val rates: SortedMap<String, BigDecimal>)