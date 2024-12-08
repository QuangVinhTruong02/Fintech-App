package com.example.fintechapp.repository

import com.example.fintechapp.common.AppShared
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.auth.AuthApiService
import com.example.fintechapp.data.request.LoginRequest
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.LoginResponse

class AuthRepository(
    private val authApiService: AuthApiService,
    private val appShared: AppShared
) {
    suspend fun signIn(loginRequest: LoginRequest): ResultApi<BaseResponse<LoginResponse>> {
        val response = authApiService.signIn(loginRequest)
        if (response.isSuccessful && response.body() != null) {
            val loginResponse: LoginResponse = response.body()!!.data
            appShared.saveToAccessToken(loginResponse.accessToken)
            appShared.saveToUserPhone(loginResponse.userResponse.phone)
            return ResultApi.Success(response.body()!!)
        } else {
            return ResultApi.Error(Exception("Failed to sign in"))
        }
    }
}