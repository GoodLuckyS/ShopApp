package com.example.shopapp.domain.user

import com.example.shopapp.domain.AppException
import com.example.shopapp.domain.LocalResponse

interface UserRepository {

    suspend fun signIn(email:String): LocalResponse<Unit>

    suspend fun signUp(data:SignUpData): LocalResponse<Unit>

    suspend fun signOut(): LocalResponse<Unit>

    suspend fun getAuthState(): LocalResponse<Boolean>
}