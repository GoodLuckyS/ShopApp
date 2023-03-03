package com.example.shopapp.domain

sealed class LocalResponse<out T> {
    data class Success<out T>(val value: T) : LocalResponse<T>()
    data class Error(val value: AppException) : LocalResponse<Nothing>()
}