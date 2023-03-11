package com.example.shopapp.ui.screens.home

import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.example.shopapp.domain.shop.usecase.GetAllShopItemsUseCase
import com.example.shopapp.domain.user.usecase.GetUserImageUseCase
import com.example.shopapp.ui.base.BaseViewModel
import com.example.shopapp.ui.models.ProductsAndCategoryUI
import com.example.shopapp.ui.models.mapToUI
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllShopItemsUseCase: GetAllShopItemsUseCase,
    private val getUserImageUseCase: GetUserImageUseCase,
) :
    BaseViewModel() {

    private val _uiState = MutableUIStateFlow<List<ProductsAndCategoryUI>>()
    val uiState = _uiState.asStateFlow()

    private var _userImageUri =
        MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val userImageUri: SharedFlow<String> = _userImageUri.asSharedFlow()

    init {

        _userImageUri.emitRequest(_uiState, { getUserImageUseCase.invoke() }) { it }

        handleRequest(_uiState, List<ProductsAndCategory>::mapToUI) {
            getAllShopItemsUseCase()
        }
    }
}