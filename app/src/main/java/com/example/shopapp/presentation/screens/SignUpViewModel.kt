package com.example.shopapp.presentation.screens

import android.util.Log
import com.example.shopapp.domain.user.SignUpData
import com.example.shopapp.domain.user.usecase.SignUpUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import com.example.shopapp.presentation.utils.Field
import com.example.shopapp.presentation.utils.FieldState
import com.example.shopapp.presentation.utils.VerifyField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    private var _isEmptyField = MutableStateFlow(FieldState())
    val isEmptyField = _isEmptyField.asStateFlow()

    fun clearError() {
        VerifyField(_isEmptyField).clear()
    }

    fun signUp(firstName: String, lastName: String, email: String) {


        VerifyField(_isEmptyField).verify(
            Field.Input(firstName),
            Field.Input(lastName),
            Field.Email(email)
        )

        Log.e("TAG", isEmptyField.value.toString())

        if (!_isEmptyField.value.error) {
            handleRequest(_uiState) {
                signUpUseCase(SignUpData(firstName, lastName, email))
            }
        }

    }

}