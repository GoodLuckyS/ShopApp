package com.example.shopapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.LocalResponse
import com.example.shopapp.presentation.entity.FieldState
import com.example.shopapp.presentation.entity.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    @Suppress("FunctionName")
    protected fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    fun <T> MutableStateFlow<UIState<T>>.reset() {
        value = UIState.Idle()
    }

    protected fun <T> handleRequest(
        state: MutableStateFlow<UIState<T>>,
        block: suspend () -> LocalResponse<T>,
    ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            block().also {
                when (it) {
                    is LocalResponse.Success -> state.value = UIState.Success(it.value)
                    is LocalResponse.Error -> state.value = UIState.Error(it.value)
                }
            }
        }

    }

    protected fun <T, M, S> MutableSharedFlow<T>.emitRequest(
        state: MutableStateFlow<UIState<S>>,
        _response: suspend () -> LocalResponse<Flow<M>>,
        mappedData: ((M) -> T),
    ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            when (val response = _response()) {
                is LocalResponse.Success -> {
                    this@emitRequest.emitAll(response.value.map {
                        mappedData(it)
                    })
                    state.value = UIState.Idle()
                }
                is LocalResponse.Error -> {
                    state.value = UIState.Error(response.value)
                }
            }
        }
    }


}