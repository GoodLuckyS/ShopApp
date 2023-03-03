package com.example.shopapp.domain.user

data class User(
    val firstName:String,
    val lastName:String,
    val email:String,
    val id : Long = UNDEFINED_ID
) {
    companion object{
      const val UNDEFINED_ID = -1L
    }
}

