package com.example.shopapp.ui.screens.registration

import com.example.shopapp.domain.user.models.SignUpData
import com.example.shopapp.domain.user.usecase.SignInGoogleUseCase
import com.example.shopapp.domain.user.usecase.SignUpUseCase
import com.example.shopapp.ui.base.BaseViewModel
import com.example.shopapp.ui.models.FieldState
import com.example.shopapp.ui.utils.Field
import com.example.shopapp.ui.utils.VerifyField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    private var _isEmptyField = MutableStateFlow(FieldState())
    val isEmptyField = _isEmptyField.asStateFlow()


    fun signUp(firstName: String, lastName: String, email: String) {

        VerifyField(_isEmptyField).verify(
            Field.Input(firstName), Field.Input(lastName), Field.Email(email)
        )

        if (!_isEmptyField.value.error) {
            handleRequest(_uiState) {
                signUpUseCase(SignUpData(firstName, lastName, email))
            }
        }

    }

    fun signInWithGoogle() {
        handleRequest(_uiState) {
            signInGoogleUseCase()
        }
    }

    fun clearError() = VerifyField(_isEmptyField).clear()

}