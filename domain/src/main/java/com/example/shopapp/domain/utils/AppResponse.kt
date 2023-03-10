package com.example.shopapp.domain.utils

sealed class AppResponse<out T> {
    data class Success<out T>(val value: T) : AppResponse<T>()
    data class Error(val value: AppError) : AppResponse<Nothing>()
}