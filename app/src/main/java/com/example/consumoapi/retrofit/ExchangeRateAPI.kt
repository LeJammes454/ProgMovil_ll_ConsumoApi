package com.example.consumoapi.retrofit

import com.example.consumoapi.retrofit.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateAPI {
    @GET("latest/{currency}")
    fun getExchangeRate(@Path("currency") currency: String): Call<ExchangeRateResponse>
}
