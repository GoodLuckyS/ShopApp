package com.example.shopapp.domain.user

import com.example.shopapp.domain.utils.AppResponse
import com.example.shopapp.domain.user.models.SignUpData

interface UserRepository {

    suspend fun signIn(email:String): AppResponse<Unit>

    suspend fun signUp(data: SignUpData): AppResponse<Unit>

    suspend fun signOut(): AppResponse<Unit>

    suspend fun getAuthState(): AppResponse<Boolean>
}