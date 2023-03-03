package com.example.shopapp.domain.user.usecase

sealed class AuthState {

    object Authorized : AuthState()

}