package com.example.shopapp.data

import com.example.shopapp.data.remote.entity.toDomain
import com.example.shopapp.data.remote.service.ShopService
import com.example.shopapp.domain.AppResponse
import com.example.shopapp.domain.shop.ShopItem
import com.example.shopapp.domain.shop.ShopList
import com.example.shopapp.domain.shop.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(private val shopService: ShopService) :
    BaseRepository(),
    ShopRepository {

    override suspend fun getAllItems(): AppResponse<ShopList> {
        return when (val latestResponse = doNetworkRequest { shopService.getLatestItems() }) {
            is AppResponse.Error -> AppResponse.Error(latestResponse.value)
            is AppResponse.Success -> {
                when (val saleResponse = doNetworkRequest { shopService.getFlashSaleItems() }) {
                    is AppResponse.Error -> AppResponse.Error(saleResponse.value)
                    is AppResponse.Success -> AppResponse.Success(
                        ShopList(
                            latestResponse.value.toDomain(),
                            saleResponse.value.toDomain()
                        )
                    )
                }
            }
        }
    }


    override suspend fun getLatestItems(): AppResponse<List<ShopItem>> {
        TODO()
    }

    override suspend fun getFlashSaleItems(): AppResponse<List<ShopItem>> {
        TODO()
    }
}