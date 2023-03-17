package com.example.consumoapi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "exchange_rates")
data class ExchangeRate(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "rate") val rate: Double
)
