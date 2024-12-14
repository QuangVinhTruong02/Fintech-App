package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ClientResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("blockedScan")
    val blockedScan: Boolean,
    @SerializedName("agencyId")
    val agencyId: Int,
    @SerializedName("point")
    val point: Int,
    @SerializedName("countGift")
    val countGift: Int,
    @SerializedName("countGiftReceived")
    val countGiftReceived: Int,
    @SerializedName("area")
    val area: Int,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("agency")
    val agency: AgencyResponse
)