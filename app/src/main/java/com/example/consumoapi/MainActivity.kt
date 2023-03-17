package com.example.consumoapi
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.consumoapi.api.ExchangeRateApi
import com.example.consumoapi.workmanager.SyncWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v6.exchangerate-api.com/v6/e635fe44bc6d624af6a33ace/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val exchangeRateApi = retrofit.create(ExchangeRateApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val syncRequest = PeriodicWorkRequest.Builder(
            SyncWorker::class.java,
            1, TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(applicationContext).enqueue(syncRequest)

        GlobalScope.launch(Dispatchers.IO) {
            val exchangeRate = exchangeRateApi.getExchangeRates("USD")
            Log.d("ExchangeRate", exchangeRate.toString())
        }
    }
}
