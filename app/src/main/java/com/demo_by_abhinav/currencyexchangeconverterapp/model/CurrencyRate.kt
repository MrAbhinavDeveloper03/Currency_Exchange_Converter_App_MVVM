package com.demo_by_abhinav.currencyexchangeconverterapp.model

import androidx.annotation.Keep
import java.util.*

@Keep
data class CurrencyRate(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: TreeMap<String,Double>,
    val timestamp: Int
)