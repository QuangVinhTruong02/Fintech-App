package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage

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