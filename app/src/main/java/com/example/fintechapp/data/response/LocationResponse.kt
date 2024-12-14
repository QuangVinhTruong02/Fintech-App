package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class LocationResponse (
    @SerializedName("address")
    val address : String,
    @SerializedName("province")
    val province : ProvinceResponse,
    @SerializedName("district")
    val district : DistrictResponse?,
    @SerializedName("ward")
    val ward : WardResponse?,
)