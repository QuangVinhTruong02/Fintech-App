package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class ImagesRequest(
    @SerializedName("url")
    val url: String
)
