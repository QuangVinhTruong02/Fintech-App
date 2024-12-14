package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.data.response.AgencyResponse

enum class AgentHeaderType {
    AgentName,
    AgentCode,
    PhoneNumber,
    City,
    FarmersCount;

    val title: String
        get() = when (this) {
            AgentName -> AppLanguage.AGENT_NAME
            AgentCode -> AppLanguage.AGENT_CODE
            PhoneNumber -> AppLanguage.PHONE_NUMBER
            City -> AppLanguage.CITY
            FarmersCount -> AppLanguage.FARMERS_COUNT
        }
}
