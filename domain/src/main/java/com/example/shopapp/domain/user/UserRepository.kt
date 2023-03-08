package com.example.shopapp.domain.user

import com.example.shopapp.domain.user.models.SignUpData
import com.example.shopapp.domain.user.models.User
import com.example.shopapp.domain.utils.AppResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signIn(email:String): AppResponse<Unit>

    suspend fun signUp(data: SignUpData): AppResponse<Unit>

    suspend fun signOut(): AppResponse<Unit>

    suspend fun getUserById() : AppResponse<User>

    fun getAuthState(): Flow<Boolean>

    suspend fun updateUserImage(uri:String) : AppResponse<Unit>

    suspend fun signInWithGoogle() : AppResponse<Unit>
}