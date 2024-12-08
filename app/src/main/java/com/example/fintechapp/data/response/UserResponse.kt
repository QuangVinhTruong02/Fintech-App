package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class UserResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("phone")
    val phone: String
)