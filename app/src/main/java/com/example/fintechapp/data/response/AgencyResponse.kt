package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AgencyResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val agentName: String,
    @SerializedName("code")
    val agentCode: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("countUser")
    val farmersCount: Int,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date,
)