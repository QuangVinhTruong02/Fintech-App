package com.example.fintechapp.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class AppShared(private val context: Context) {
    private val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = "storeData")

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    suspend fun saveToDataStore(accessTokenn: String) {
        context.preferenceDataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessTokenn
        }
    }

    suspend fun getAccessToken(): String {
        val preferences = context.preferenceDataStore.data.first() // Lấy dữ liệu 1 lần
        return preferences[ACCESS_TOKEN] ?: ""
    }

    suspend fun clearDataStore() = context.preferenceDataStore.edit {
        it.clear()
    }
}