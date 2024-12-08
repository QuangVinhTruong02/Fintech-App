package com.example.fintechapp.data.response

import com.google.gson.annotations.SerializedName

data class AgenciesResponse(
    @SerializedName("items")
    val agentList: List<AgencyResponse>,
    @SerializedName("count")
    val count: Int
)
