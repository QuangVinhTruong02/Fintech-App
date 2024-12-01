package com.example.fintechapp.core.helper

import com.example.fintechapp.common.AppLanguage

class Validation {
    fun validateEmail(value: String): String? {
        val emailRegex = Regex(
            "^[a-zA-Z0-9.a-zA-Z0-9.!#\$%&'*+_/=?^_{|}~]+@[a-zA-Z0-9]+\\.[a-zA-Z]+"
        )
        return when {
            value.isEmpty() -> AppLanguage.FIELD_CANNOT_BE_EMPTY_ERROR
            !emailRegex.matches(value) -> AppLanguage.PLEASE_ENTER_A_VALID_EMAIL_ADDRESS
            else -> null
        }
    }

    fun validatePassword(value: String): String? {
        return when {
            value.isEmpty() -> AppLanguage.FIELD_CANNOT_BE_EMPTY_ERROR
            value.length < 8 -> AppLanguage.PASSWORD_MINIMUM_EIGHT_CHARACTERS
            else -> null
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): String? {
        return when {
            confirmPassword.isEmpty() -> AppLanguage.FIELD_CANNOT_BE_EMPTY_ERROR
            password != confirmPassword -> AppLanguage.DOES_NOT_MATCH_THE_PASSWORD
            else -> null
        }
    }

    fun validateEmpty(value: String): String? {
        if (value.isEmpty()) {
            return AppLanguage.FIELD_CANNOT_BE_EMPTY_ERROR
        }
        return null
    }
}