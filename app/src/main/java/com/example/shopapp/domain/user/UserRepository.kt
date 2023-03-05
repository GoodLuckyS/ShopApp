package com.example.shopapp.domain.user

import com.example.shopapp.domain.AppResponse

interface UserRepository {

    suspend fun signIn(email:String): AppResponse<Unit>

    suspend fun signUp(data:SignUpData): AppResponse<Unit>

    suspend fun signOut(): AppResponse<Unit>

    suspend fun getAuthState(): AppResponse<Boolean>
}