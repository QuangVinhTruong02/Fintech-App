package com.example.fintechapp.core.helper

import com.example.fintechapp.common.AppLanguage

class Validation {
    fun validatePhoneNumber(value: String): String? {
        return when {
            value.isEmpty() -> AppLanguage.FIELD_CANNOT_BE_EMPTY_ERROR
//            value.length != 10 -> AppLanguage.PHONE_NUMBER_DIGIT_TEN_CHARACTERS_ERROR
            else -> null
        }
    }
}