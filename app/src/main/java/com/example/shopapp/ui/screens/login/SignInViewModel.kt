package com.example.shopapp.ui.screens.login

import com.example.shopapp.domain.user.usecase.SignInUseCase
import com.example.shopapp.ui.base.BaseViewModel
import com.example.shopapp.ui.models.FieldState
import com.example.shopapp.ui.utils.Field
import com.example.shopapp.ui.utils.VerifyField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    private var _isEmptyField = MutableStateFlow(FieldState())
    val isEmptyField = _isEmptyField.asStateFlow()


    fun signIn(email: String) {

        VerifyField(_isEmptyField).verify(Field.Input(email))

        if (!_isEmptyField.value.error) {
            handleRequest(_uiState) {
                signInUseCase(email)
            }
        }

    }

    fun clearError() = VerifyField(_isEmptyField).clear()

}