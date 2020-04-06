package com.reactivemobile.app.presentation.util

import java.math.BigDecimal
import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("###,###.###")

fun formatAmount(rate: BigDecimal) = decimalFormat.format(rate.toDouble())
