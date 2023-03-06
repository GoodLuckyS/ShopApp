package com.example.shopapp.data

abstract class DataException() : RuntimeException() {
    abstract override val message: String
}

class UniqueEmailException : DataException() {
    override val message  = "An account with this email already exists"
}

class SignInException : DataException() {
    override val message: String = "Check your Username or Password"
}