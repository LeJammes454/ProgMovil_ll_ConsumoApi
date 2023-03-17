package com.example.consumoapi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRate(
    @PrimaryKey val code: String,
    @ColumnInfo(name = "rate") val rate: Double
)