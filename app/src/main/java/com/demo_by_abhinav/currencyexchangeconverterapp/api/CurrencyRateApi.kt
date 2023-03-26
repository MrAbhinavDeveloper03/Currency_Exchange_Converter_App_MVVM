package com.demo_by_abhinav.currencyexchangeconverterapp.api

import com.demo_by_abhinav.currencyexchangeconverterapp.model.CurrencyRate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRateApi {

    @GET("latest.json?app_id=<APP_ID>")
    suspend fun fetchRates():Response<String>

//    @GET("latest.json")
//    suspend fun getLatestRates(
//        @Query("app_id") appId: String
//    ): CurrencyRate


}
