package com.example.consumoapi.workmanager

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.consumoapi.AppDatabase
import com.example.consumoapi.ExchangeRate
import com.example.consumoapi.api.ExchangeRateApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SyncWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v6.exchangerate-api.com/v6/e635fe44bc6d624af6a33ace/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val exchangeRateApi = retrofit.create(ExchangeRateApi::class.java)

    override fun doWork(): Result {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        GlobalScope.launch(Dispatchers.IO) {
            val exchangeRate = exchangeRateApi.getExchangeRates("USD")
            db.exchangeRateDao().insertExchangeRate(ExchangeRate("USD", exchangeRate.conversionRates["EUR"] ?: 0.0))
            Log.d("ExchangeRate", "Exchange rate saved to database")
        }
        return Result.success()
    }
}

