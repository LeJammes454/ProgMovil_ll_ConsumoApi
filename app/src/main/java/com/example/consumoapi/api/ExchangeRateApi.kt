package com.example.consumoapi.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("latest/{base}")
    suspend fun getExchangeRates(@Path("base") base: String): ExchangeRateResponse
}

data class ExchangeRateResponse(
    @SerializedName("result") val result: String,
    @SerializedName("conversion_rates") val conversionRates: Map<String, Double>
)
