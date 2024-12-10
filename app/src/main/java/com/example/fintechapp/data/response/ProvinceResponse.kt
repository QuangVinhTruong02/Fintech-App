package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)