package com.example.fintechapp.common.extensions

fun String.generateInitials(): String {
    return this.split(" ")
        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
        .joinToString("")
}