package com.example.shopapp.ui.screens.home

import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.example.shopapp.domain.shop.usecase.GetAllShopItemsUseCase
import com.example.shopapp.ui.base.BaseViewModel
import com.example.shopapp.ui.models.ProductsAndCategoryUI
import com.example.shopapp.ui.models.mapToUI
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllShopItemsUseCase: GetAllShopItemsUseCase) :
    BaseViewModel() {

    private val _uiState = MutableUIStateFlow<List<ProductsAndCategoryUI>>()
    val uiState = _uiState.asStateFlow()

    init {
        handleRequest(_uiState,List<ProductsAndCategory>::mapToUI){
            getAllShopItemsUseCase()
        }
    }
}