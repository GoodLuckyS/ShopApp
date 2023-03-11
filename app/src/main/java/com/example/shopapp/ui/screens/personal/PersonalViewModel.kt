package com.example.shopapp.ui.screens.personal

import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.user.models.User
import com.example.shopapp.domain.user.usecase.GetUserUseCase
import com.example.shopapp.domain.user.usecase.SignOutUseCase
import com.example.shopapp.domain.user.usecase.UpdateUserImageUseCase
import com.example.shopapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserImageUseCase: UpdateUserImageUseCase,
) : BaseViewModel() {

    private var _uiState = MutableUIStateFlow<Unit>()
    val uiState = _uiState.asStateFlow()

    private var _user =
        MutableSharedFlow<User>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val user: SharedFlow<User> = _user

    init {
        _user.emitRequest(_uiState, {
            getUserUseCase.invoke()
        }) { it }
    }

    fun signOut() {
        handleRequest(_uiState) {
            signOutUseCase.invoke()
        }
    }

    fun updateUserImage(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateUserImageUseCase(uri)
        }
    }
}