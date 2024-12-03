package com.example.fintechapp.repository

import com.example.fintechapp.common.AppShared
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.remote.service.auth.AuthApiService
import com.example.fintechapp.data.request.LoginRequest
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.LoginResponse

//interface AuthRepository {
//    //    suspend fun signInAmplify(userName: String, password: String): ResultApi<String>
//    suspend fun signIn(loginRequest: LoginRequest): ResultApi<BaseResponse<LoginResponse>>
//}

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val appShared: AppShared
) {
    suspend fun signIn(loginRequest: LoginRequest): ResultApi<BaseResponse<LoginResponse>> {
        val response = authApiService.signIn(loginRequest)
//        Log.d("Check my app", "${appShared::getAccessToken}")
        if (response.isSuccessful && response.body() != null) {
            val loginResponse: LoginResponse = response.body()!!.data
            appShared.saveToDataStore(loginResponse.accessToken)
            return ResultApi.Success(response.body()!!)
        } else {
            return ResultApi.Error(Exception("Failed to sign in"))
        }
    }
}