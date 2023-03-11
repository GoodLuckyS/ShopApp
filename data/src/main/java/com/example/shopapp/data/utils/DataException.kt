package com.example.shopapp.data.utils

abstract class DataException : RuntimeException() {
    abstract override val message: String
}

class UniqueException : DataException() {
    override val message = "An account with this email/name already exists!"
}

class SignInException : DataException() {
    override val message: String = "Check your Username or Password!"
}

class AuthException : DataException() {
    override val message: String = "Occurred Error!"
}
