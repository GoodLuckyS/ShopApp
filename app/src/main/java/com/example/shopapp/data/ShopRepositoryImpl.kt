package com.example.shopapp.data

import com.example.shopapp.data.remote.service.ShopService
import com.example.shopapp.domain.AppResponse
import com.example.shopapp.domain.shop.Product
import com.example.shopapp.domain.shop.ProductsAndCategory
import com.example.shopapp.domain.shop.ShopList
import com.example.shopapp.domain.shop.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(private val shopService: ShopService) :
    BaseRepository(),
    ShopRepository {

    override suspend fun getAllProductsList(): AppResponse<List<ProductsAndCategory>> {
       return when (val latestResponse = getLatestItems()) {
            is AppResponse.Error -> AppResponse.Error(latestResponse.value)
            is AppResponse.Success -> {
                when (val saleResponse = getFlashSaleItems()) {
                    is AppResponse.Error -> AppResponse.Error(saleResponse.value)
                    is AppResponse.Success -> AppResponse.Success(
                        listOf(latestResponse.value,saleResponse.value)
                    )
                }
            }
        }
    }

    override suspend fun getProductById(id: Int) {
        TODO("Not yet implemented")
    }


    private suspend fun getLatestItems(): AppResponse<ProductsAndCategory> = doNetworkRequest {
        shopService.getLatestItems()
    }

    private suspend fun getFlashSaleItems(): AppResponse<ProductsAndCategory> = doNetworkRequest {
        shopService.getFlashSaleItems()
    }
}