package com.example.fintechapp.data.remote

sealed class ResultApi<out R> {
    data class Success<out T>(val data: T?) : ResultApi<T>()
    data class Error(val exception: Exception) : ResultApi<Nothing>()
}

fun <T> ResultApi<T>.successOr(fallback: T): T {
    return (this as? ResultApi.Success<T>)?.data ?: fallback
}

