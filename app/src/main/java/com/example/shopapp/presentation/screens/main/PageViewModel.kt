package com.example.shopapp.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.shop.ProductsAndCategory
import com.example.shopapp.domain.shop.usecase.GetAllShopItemsUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import com.example.shopapp.presentation.entity.ProductAndCategoryUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageViewModel @Inject constructor(private val getAllShopItemsUseCase: GetAllShopItemsUseCase) : BaseViewModel() {

    private val _uiState = MutableUIStateFlow<List<ProductsAndCategory>>()
    val uiState = _uiState.asStateFlow()

    private var _categoryList = MutableSharedFlow<List<ProductsAndCategory>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val categoryList: SharedFlow<List<ProductsAndCategory>> = _categoryList

    init {
        viewModelScope.launch {
            handleRequest(_uiState){
                getAllShopItemsUseCase()
            }
        }
    }
}