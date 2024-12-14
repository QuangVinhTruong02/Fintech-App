package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class ClientsResponse(
    @SerializedName("items")
    val clientList : List<ClientResponse>,
    @SerializedName("count")
    val count : Int,
)