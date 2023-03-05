package com.example.shopapp.presentation.screens.personal

import com.example.shopapp.domain.user.usecase.SignOutUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PersonalViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    fun signOut() {
        handleRequest(_uiState) {
            signOutUseCase()
        }
    }

}