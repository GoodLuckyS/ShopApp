package com.example.shopapp.presentation.screens.home

import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.example.shopapp.domain.shop.usecase.GetAllShopItemsUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllShopItemsUseCase: GetAllShopItemsUseCase) :
    BaseViewModel() {

    private val _uiState = MutableUIStateFlow<List<ProductsAndCategory>>()
    val uiState = _uiState.asStateFlow()

    init {
        handleRequest(_uiState) {
            getAllShopItemsUseCase()
        }
    }
}