package com.example.shopapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.utils.AppResponse
import com.example.shopapp.presentation.models.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    @Suppress("FunctionName")
    protected fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())


    protected fun <T> handleRequest(
        state: MutableStateFlow<UIState<T>>,
        block: suspend () -> AppResponse<T>,

        ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            block().also {
                when (it) {
                    is AppResponse.Success -> state.value = UIState.Success(it.value)
                    is AppResponse.Error -> state.value = UIState.Error(it.value)
                }
            }
        }
    }

    protected fun <T, M> handleRequest(
        state: MutableStateFlow<UIState<T>>,
        mappedData: ((M) -> T),
        block: suspend () -> AppResponse<M>,

        ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            block().also {
                when (it) {
                    is AppResponse.Success -> state.value = UIState.Success(mappedData(it.value))
                    is AppResponse.Error -> state.value = UIState.Error(it.value)
                }
            }
        }
    }

    protected fun <T, M, S> MutableSharedFlow<T>.emitRequest(
        state: MutableStateFlow<UIState<S>>,
        _response: suspend () -> AppResponse<Flow<M>>,
        mappedData: ((M) -> T),
    ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            when (val response = _response()) {
                is AppResponse.Success -> {
                    this@emitRequest.emitAll(response.value.map {
                        mappedData(it)
                    })
                    state.value = UIState.Idle()
                }
                is AppResponse.Error -> {
                    state.value = UIState.Error(response.value)
                }
            }
        }
    }


}