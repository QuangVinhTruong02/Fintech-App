package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("provinceCode")
    val provinceCode: String,
    @SerializedName("name")
    val name: String
)