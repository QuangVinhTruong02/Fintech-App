package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CodeProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nameProduct: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("images")
    val images: List<ImageResponse>,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("updatedAt")
    val updatedAt: Date,
    @SerializedName("nextProduct")
    val nextProduct: CodeProductResponse?,
)
