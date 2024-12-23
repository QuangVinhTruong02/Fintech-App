package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class CodeProductsResponse(
    @SerializedName("items")
    val codeProductList: List<CodeProductResponse>,
    @SerializedName("count")
    val count: Int
)
