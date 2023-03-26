package com.demo_by_abhinav.currencyexchangeconverterapp.api

import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

interface CurrencyRateApiInstance {
    companion object{
        val api: CurrencyRateApi by lazy{
            Retrofit.Builder()
                .baseUrl("https://openexchangerates.org/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(CurrencyRateApi::class.java)


    }
//        @Provides
//        @Singleton
//        fun api(
//            okHttpClient: OkHttpClient,
//        ): Retrofit {
//            return Retrofit.Builder().baseUrl("https://openexchangerates.org/api/")
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//
//        @Provides
//        @Singleton
//        fun provideNetworkService(retrofit: Retrofit): CurrencyRateApi =
//            retrofit.create(CurrencyRateApi::class.java)

    }
}