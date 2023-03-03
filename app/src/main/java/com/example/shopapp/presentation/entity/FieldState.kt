package com.example.shopapp.presentation.entity

data class FieldState(
    val fieldInputMessage: String = "",
    val fieldEmailMessage: String = "",
    val error : Boolean = false
)