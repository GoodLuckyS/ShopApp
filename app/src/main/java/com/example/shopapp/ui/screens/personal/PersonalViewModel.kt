package com.example.shopapp.ui.screens.personal

import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.user.models.User
import com.example.shopapp.domain.user.usecase.GetUserUseCase
import com.example.shopapp.domain.user.usecase.SignOutUseCase
import com.example.shopapp.domain.user.usecase.UpdateUserImageUseCase
import com.example.shopapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserImageUseCase: UpdateUserImageUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<User>()
    val uiState = _uiState.asStateFlow()

    init {
        handleRequest(_uiState) {
            getUserUseCase()
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            signOutUseCase()
        }
    }

    fun updateUserImage(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserImageUseCase(uri)
        }
    }
}