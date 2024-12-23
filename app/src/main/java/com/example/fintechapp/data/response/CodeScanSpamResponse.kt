package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class CodeScanSpamResponse(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("published")
    val published: Boolean,
)
