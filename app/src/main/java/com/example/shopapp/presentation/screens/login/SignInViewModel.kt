package com.example.shopapp.presentation.screens.login

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.shop.usecase.GetAllShopItemsUseCase
import com.example.shopapp.domain.user.usecase.SignInUseCase
import com.example.shopapp.domain.user.usecase.SignUpUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel@Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val soap : GetAllShopItemsUseCase
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val request = soap()
        }

    }

    fun signIn(email: String) {
        handleRequest(_uiState){
            signInUseCase(email)
        }
    }

}