package com.example.shopapp.ui

import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.user.usecase.GetAuthStateUseCase
import com.example.shopapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
) : BaseViewModel() {

    val auth: StateFlow<Boolean> = getAuthStateUseCase().stateIn(
        viewModelScope,
        SharingStarted.Eagerly, false
    )
}