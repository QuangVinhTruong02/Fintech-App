package com.example.fintechapp.data.remote.service.auth


import com.example.fintechapp.data.request.LoginRequest
import com.example.fintechapp.data.response.BaseResponse
import com.example.fintechapp.data.response.LoginResponse
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login")
    suspend fun signIn(@Body request: LoginRequest): Response<BaseResponse<LoginResponse>>
}