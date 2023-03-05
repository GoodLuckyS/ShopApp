package com.example.shopapp.domain

sealed class AppError {

    abstract val message : String

    class Api(override val message: String) : AppError()
    class Unexpected(override val message: String) : AppError()
}






