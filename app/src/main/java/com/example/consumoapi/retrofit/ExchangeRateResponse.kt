package com.example.consumoapi.retrofit

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    @SerializedName("conversion_rates")
    val conversionRates: Map<String, Double>
)
