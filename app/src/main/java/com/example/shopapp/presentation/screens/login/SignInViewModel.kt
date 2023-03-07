package com.example.shopapp.presentation.screens.login

import com.example.shopapp.domain.user.usecase.SignInUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()


    fun signIn(email: String) {
        handleRequest(_uiState) {
            signInUseCase(email)
        }
    }

}