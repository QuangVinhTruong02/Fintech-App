package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage

enum class AgentHeaderType {
    AGENT_NAME,
    AGENT_CODE,
    PHONE_NUMBER,
    CITY,
    FARMER_COUNT;

    val title: String
        get() = when (this) {
            AGENT_NAME -> AppLanguage.AGENT_NAME
            AGENT_CODE -> AppLanguage.AGENT_CODE
            PHONE_NUMBER -> AppLanguage.PHONE_NUMBER
            CITY -> AppLanguage.CITY
            FARMER_COUNT -> AppLanguage.FARMERS_COUNT
        }
}
