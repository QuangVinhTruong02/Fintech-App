package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class LocationRequest(
    @SerializedName("provinceCode")
    val provinceCode: String,
    @SerializedName("districtCode")
    val districtCode: String,
    @SerializedName("wardCode")
    val wardCode: String,
    @SerializedName("address")
    val address: String
)