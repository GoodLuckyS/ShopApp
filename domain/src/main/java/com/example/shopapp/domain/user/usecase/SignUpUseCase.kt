package com.example.shopapp.domain.user.usecase

import com.example.shopapp.domain.user.UserRepository
import com.example.shopapp.domain.user.models.SignUpData
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(data: SignUpData) = repository.signUp(data)
}