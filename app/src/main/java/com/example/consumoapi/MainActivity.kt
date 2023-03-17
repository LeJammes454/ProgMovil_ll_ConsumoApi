package com.example.consumoapi


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.consumoapi.retrofit.ExchangeRateAPI
import com.example.consumoapi.retrofit.ExchangeRateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/v6/e635fe44bc6d624af6a33ace/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val exchangeRateAPI = retrofit.create(ExchangeRateAPI::class.java)
        val call = exchangeRateAPI.getExchangeRate("USD")
        call.enqueue(object : Callback<ExchangeRateResponse> {
            override fun onResponse(
                call: Call<ExchangeRateResponse>,
                response: Response<ExchangeRateResponse>
            ) {
                val conversionRates = response.body()?.conversionRates
                Log.d("ExchangeRate", conversionRates.toString())
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                Log.e("ExchangeRate", t.message, t)
            }
        })

    }
}
