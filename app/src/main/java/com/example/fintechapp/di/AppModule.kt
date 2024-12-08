package com.example.fintechapp.di

import android.content.Context
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppShared
import com.example.fintechapp.data.remote.ApiInterceptor
import com.example.fintechapp.data.remote.service.agency.AgencyApiService
import com.example.fintechapp.data.remote.service.auth.AuthApiService
import com.example.fintechapp.repository.AgencyRepository
import com.example.fintechapp.repository.AuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {
    private lateinit var applicationContext: Context

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create();
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()
    }

    private val authApiService: AuthApiService by lazy {
        Retrofit.Builder().baseUrl(AppConst.BASE_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AuthApiService::class.java)
    }
    val authRepository: AuthRepository by lazy {
        AuthRepository(authApiService, appShared)
    }

    private val agencyApiService: AgencyApiService by lazy {
        Retrofit.Builder().baseUrl(AppConst.BASE_AGENCY_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AgencyApiService::class.java)
    }

    val agencyRepository: AgencyRepository by lazy {
        AgencyRepository(agencyApiService)
    }

    fun initContext(context: Context) {
        applicationContext = context.applicationContext
    }

    val appShared: AppShared by lazy {
        AppShared(applicationContext)
    }
}