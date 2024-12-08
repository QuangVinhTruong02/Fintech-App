package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("user")
    val userResponse: UserResponse
)
