package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("rememberMe")
    val rememberMe: Int = 1
)
