package com.example.shopapp.presentation.screens.page2

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.user.usecase.SignInUseCase
import com.example.shopapp.domain.user.usecase.SignUpUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel@Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    fun signIn(email: String) {
        handleRequest(_uiState){
            signInUseCase(email)
        }
    }

}