package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class WardResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("districtCode")
    val districtCode: String,
    @SerializedName("name")
    val name: String

)