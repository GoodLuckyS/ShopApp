package com.example.shopapp.data.remote

import com.example.shopapp.data.BaseRepository
import com.example.shopapp.data.remote.service.ShopService
import com.example.shopapp.domain.utils.AppResponse
import com.example.shopapp.domain.shop.models.ProductAndDetails
import com.example.shopapp.domain.shop.models.ProductsAndCategory
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(private val shopService: ShopService) :
    BaseRepository(),
    com.example.shopapp.domain.shop.ShopRepository {

    override suspend fun getAllProductsList(): AppResponse<List<ProductsAndCategory>> {
        return when (val latestResponse = getLatestItems()) {
            is AppResponse.Error -> AppResponse.Error(latestResponse.value)
            is AppResponse.Success -> {
                when (val saleResponse = getFlashSaleItems()) {
                    is AppResponse.Error -> AppResponse.Error(saleResponse.value)
                    is AppResponse.Success -> AppResponse.Success(
                        listOf(latestResponse.value, saleResponse.value)
                    )

                }
            }
        }
    }

    override suspend fun getProductById(id: String): AppResponse<ProductAndDetails> = doNetworkRequest {
        shopService.getItemDetails()
    }

    private suspend fun getLatestItems(): AppResponse<ProductsAndCategory> = doNetworkRequest {
        shopService.getLatestItems()
    }

    private suspend fun getFlashSaleItems(): AppResponse<ProductsAndCategory> = doNetworkRequest {
        shopService.getFlashSaleItems()
    }
}