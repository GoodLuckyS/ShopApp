package com.example.shopapp.domain.user.usecase

import android.util.Log
import com.example.shopapp.domain.user.SignUpData
import com.example.shopapp.domain.user.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(data: SignUpData) = repository.signUp(data)
}