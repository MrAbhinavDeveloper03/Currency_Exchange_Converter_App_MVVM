package com.demo_by_abhinav.currencyexchangeconverterapp.repository

import com.demo_by_abhinav.currencyexchangeconverterapp.Entity.CurrencyExchange
import com.demo_by_abhinav.currencyexchangeconverterapp.dao.CurrencyExchangeDao

class CurrencyExchangeRepo(private val currencyExchangeDao: CurrencyExchangeDao) {
    val currencyExchangeList=currencyExchangeDao.getRates()

    suspend fun insertCurrencyRates(currencyExchangeList: ArrayList<CurrencyExchange>){
         for (item in currencyExchangeList)
             currencyExchangeDao.insert(item)
    }
}