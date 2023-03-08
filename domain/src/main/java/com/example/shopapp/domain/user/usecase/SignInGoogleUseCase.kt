package com.example.shopapp.domain.user.usecase

import com.example.shopapp.domain.user.UserRepository
import javax.inject.Inject

class SignInGoogleUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.signInWithGoogle()
}