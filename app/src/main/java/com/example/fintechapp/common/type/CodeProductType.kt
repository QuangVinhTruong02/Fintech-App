package com.example.fintechapp.common.type

import com.example.fintechapp.common.AppLanguage

enum class CodeProductType {
    PRODUCT_NAME,
    NEXT_PRODUCT,
    DESCRIPTION;

    val title: String
        get() = when (this) {
            PRODUCT_NAME -> AppLanguage.PRODUCT_NAME
            NEXT_PRODUCT -> AppLanguage.NEXT_PRODUCT
            DESCRIPTION -> AppLanguage.DESCRIPTION
        }
}