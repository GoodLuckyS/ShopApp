package com.example.shopapp.domain.user.usecase

import com.example.shopapp.domain.user.UserRepository
import javax.inject.Inject

class GetAuthStateUseCase@Inject constructor(private val repository: UserRepository) {
   operator fun invoke() = repository.getAuthState()
}