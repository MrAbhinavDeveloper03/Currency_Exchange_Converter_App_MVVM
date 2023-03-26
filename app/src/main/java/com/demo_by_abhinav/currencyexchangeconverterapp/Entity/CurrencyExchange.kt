package com.demo_by_abhinav.currencyexchangeconverterapp.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rates")
class CurrencyExchange(@PrimaryKey @ColumnInfo(name = "currencyName") val currencyName: String,
                       @ColumnInfo(name = "rate") val rate:Double)