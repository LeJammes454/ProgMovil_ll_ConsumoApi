package com.example.consumoapi.room

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRate: ExchangeRate)

    @Query("SELECT * FROM exchange_rates ORDER BY id DESC LIMIT 1")
    suspend fun getLatestExchangeRate(): ExchangeRate?

    @Query("SELECT * FROM exchange_rates WHERE id = :id")
    fun getExchangeRateCursor(id: Long): Cursor
}
