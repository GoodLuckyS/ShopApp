package com.example.shopapp.domain.user.usecase

import com.example.shopapp.domain.user.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(firstName: String) = repository.signIn(firstName)

}