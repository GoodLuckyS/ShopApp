package com.example.shopapp.domain.utils

sealed class AppError {

    abstract val message : String

    class Api(override val message: String) : AppError()
    class Unexpected(override val message: String) : AppError()
}






