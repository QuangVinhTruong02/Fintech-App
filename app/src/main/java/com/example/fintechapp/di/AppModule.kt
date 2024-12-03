package com.example.fintechapp.di

import android.content.Context
import android.util.Log
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppShared
import com.example.fintechapp.data.remote.service.auth.AuthApiService
import com.example.fintechapp.repository.AuthRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {
    private lateinit var applicationContext: Context

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create();
    }
    val authApiService: AuthApiService by lazy {
        try {
            Retrofit.Builder().baseUrl(AppConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(AuthApiService::class.java)
        } catch (e: Exception) {
            Log.e("AppModuleImpl", "Retrofit initialization failed", e)
            throw e // Rethrow exception để dễ dàng bắt lỗi
        }
    }
    val authRepository: AuthRepositoryImpl by lazy {
        AuthRepositoryImpl(authApiService, appShared)
    }

    fun initContext(context: Context) {
        applicationContext = context.applicationContext
    }
    val appShared: AppShared by lazy {
        AppShared(applicationContext)
    }
}