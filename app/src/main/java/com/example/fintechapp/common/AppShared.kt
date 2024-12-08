package com.example.fintechapp.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


class AppShared(private val context: Context) {
    private val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = "storeData")

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val USER_PHONE = stringPreferencesKey("user_phone")
    }

    suspend fun saveToUserPhone(phoneNumber: String) {
        context.preferenceDataStore.edit { preferences ->
            preferences[USER_PHONE] = phoneNumber
        }
    }

    suspend fun getUserPhone(): String {
        val preferences = context.preferenceDataStore.data.first() // Lấy dữ liệu 1 lần
        return preferences[USER_PHONE] ?: ""
    }

    suspend fun saveToAccessToken(accessToken: String) {
        context.preferenceDataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun getAccessToken(): String {
        val preferences = context.preferenceDataStore.data.first() // Lấy dữ liệu 1 lần
        return preferences[ACCESS_TOKEN] ?: ""
    }

    suspend fun logOut() = context.preferenceDataStore.edit { preferences ->
        preferences.remove(ACCESS_TOKEN)
        preferences.remove(USER_PHONE)
    }
}