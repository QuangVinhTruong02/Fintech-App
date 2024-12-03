package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class BaseResponse<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: T
)