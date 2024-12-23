package com.example.fintechapp.data.request

import com.google.gson.annotations.SerializedName

data class CodeProductRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: List<ImagesRequest>,
    @SerializedName("nextProductId")
    val nextProductId: Int?
)