package com.example.shopapp.presentation.models

import com.example.shopapp.domain.utils.AppError

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Error<T>(val error: AppError) : UIState<T>()
}
