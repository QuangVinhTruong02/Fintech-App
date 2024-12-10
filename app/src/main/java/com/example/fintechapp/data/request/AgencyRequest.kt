package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class AgencyRequest (
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("location")
    val location: LocationRequest,
)