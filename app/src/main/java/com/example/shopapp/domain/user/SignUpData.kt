package com.example.shopapp.domain.user

import com.example.shopapp.data.local.user.models.UserDbEntity

data class SignUpData(
    val firstName: String,
    val lastName: String,
    val email : String,
){
    fun toData() = UserDbEntity(
        id = 0,
        firstName, lastName, email
    )
}