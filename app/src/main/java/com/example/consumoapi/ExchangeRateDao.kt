package com.example.consumoapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRate: ExchangeRate)

    @Query("SELECT * FROM exchange_rates WHERE code = :code")
    suspend fun getExchangeRate(code: String): ExchangeRate
}