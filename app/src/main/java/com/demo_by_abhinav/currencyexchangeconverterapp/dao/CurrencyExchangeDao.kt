package com.demo_by_abhinav.currencyexchangeconverterapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo_by_abhinav.currencyexchangeconverterapp.Entity.CurrencyExchange

@Dao
interface CurrencyExchangeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyExchange: CurrencyExchange)

    @Query("select * from currency_rates order by currencyName ASC")
    fun getRates():LiveData<List<CurrencyExchange>>
}