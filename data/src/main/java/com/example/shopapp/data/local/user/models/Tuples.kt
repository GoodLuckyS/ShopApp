package com.example.shopapp.data.local.user.models

data class UserSignInTuple(
    val id: Long,
)

data class UserUpdateImageTuple(
    val id: Long,
    val uri: String,
)