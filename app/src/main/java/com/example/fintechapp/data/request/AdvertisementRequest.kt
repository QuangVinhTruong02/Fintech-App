package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class AdvertisementRequest(
    @SerializedName("value")
    val value: Double,
    @SerializedName("isPublished")
    val isPublished: Boolean,
)