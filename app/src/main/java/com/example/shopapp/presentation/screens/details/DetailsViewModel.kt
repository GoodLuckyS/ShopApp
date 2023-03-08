package com.example.shopapp.presentation.screens.details

import com.example.shopapp.domain.shop.models.ProductAndDetails
import com.example.shopapp.domain.shop.usecase.GetProductByIdUseCase
import com.example.shopapp.presentation.base.BaseViewModel
import com.example.shopapp.presentation.models.ProductAndDetailsUI
import com.example.shopapp.presentation.models.mapToUI
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : BaseViewModel() {

    private val _uiState = MutableUIStateFlow<ProductAndDetailsUI>()
    val uiState = _uiState.asStateFlow()

    init {
        handleRequest(_uiState, ProductAndDetails::mapToUI){
            getProductByIdUseCase()
        }
    }

}