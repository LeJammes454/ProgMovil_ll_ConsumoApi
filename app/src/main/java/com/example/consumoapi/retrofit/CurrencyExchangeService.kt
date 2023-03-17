package com.example.consumoapi.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyExchangeService {
    @GET("latest/{base}")
    fun getExchangeRates(@Path("base") base: String): Call<ExchangeRates>
}
