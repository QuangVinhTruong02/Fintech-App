package com.example.fintechapp.data.remote

import com.example.fintechapp.common.AppShared
import com.example.fintechapp.di.AppModule
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor(private val appShared: AppShared = AppModule.appShared) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { appShared.getAccessToken() }
        val originalRequest: Request = chain.request()
        val requestBuilder =
            originalRequest.newBuilder().addHeader("Authorization", "Bearer $accessToken")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}