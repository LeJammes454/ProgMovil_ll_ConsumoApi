package com.example.consumoapi

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.consumoapi.room.AppDatabase
import com.example.consumoapi.room.ExchangeRateDao
class ExchangeRateContentProvider : ContentProvider() {
    private lateinit var exchangeRateDao: ExchangeRateDao

    override fun onCreate(): Boolean {
        val context = context ?: return false
        exchangeRateDao = AppDatabase.getInstance(context).exchangeRateDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            URI_EXCHANGE_RATE -> exchangeRateDao.getExchangeRateCursor(ContentUris.parseId(uri))
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            URI_EXCHANGE_RATE -> "vnd.android.cursor.item/exchange_rate"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null // not implemented
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0 // not implemented
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0 // not implemented
    }

    companion object {
        private const val AUTHORITY = "com.example.exchange_rate.provider"
        private const val PATH_EXCHANGE_RATE = "exchange_rate"
        private const val URI_EXCHANGE_RATE = 1

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH_EXCHANGE_RATE + "/#", URI_EXCHANGE_RATE)
        }
    }
}
