package com.example.shopapp.domain.shop

import com.example.shopapp.domain.AppResponse

interface ShopRepository {

    suspend fun getAllItems() : AppResponse<ShopList>

    suspend fun getLatestItems() : AppResponse<List<ShopItem>>

    suspend fun getFlashSaleItems() : AppResponse<List<ShopItem>>

}