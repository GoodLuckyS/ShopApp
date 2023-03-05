package com.example.shopapp.presentation.entity

import com.example.shopapp.domain.AppError

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Error<T>(val error: AppError) : UIState<T>()
}
