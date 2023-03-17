package com.example.consumoapi

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.consumoapi.retrofit.CurrencyExchangeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRateWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/e635fe44bc6d624af6a33ace/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyExchangeService::class.java)
        val call = service.getExchangeRates("USD")

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val exchangeRates = response.body()
                exchangeRates?.conversion_rates?.forEach { (currency, rate) ->
                    Log.d("ExchangeRate", "$currency: $rate")
                }
                return Result.success()
            }
        } catch (e: Exception) {
            Log.e("ExchangeRate", "Failed to get exchange rates", e)
        }

        return Result.failure()
    }
}
