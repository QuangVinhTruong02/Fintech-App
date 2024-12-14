package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.type.AgentHeaderType.AgentCode
import com.example.fintechapp.common.type.AgentHeaderType.AgentName
import com.example.fintechapp.common.type.AgentHeaderType.City
import com.example.fintechapp.common.type.AgentHeaderType.FarmersCount
import com.example.fintechapp.common.type.AgentHeaderType.PhoneNumber

enum class ClientOfAgentHeaderType {
    CLIENT,
    PHONE_NUMBER,
    CITY,
    CREATION_DATE;

    val title: String
        get() = when (this) {
            CLIENT -> AppLanguage.CLIENT
            PHONE_NUMBER -> AppLanguage.PHONE_NUMBER
            CITY -> AppLanguage.CITY
            CREATION_DATE -> AppLanguage.CREATION_DATE
        }
}