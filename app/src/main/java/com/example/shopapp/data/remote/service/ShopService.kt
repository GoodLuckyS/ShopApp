package com.example.shopapp.data.remote.service

import com.example.shopapp.data.remote.entity.FlashSaleDTO
import com.example.shopapp.data.remote.entity.LatestDTO
import retrofit2.Response
import retrofit2.http.GET

interface ShopService {

    @GET(LATEST_URL)
    suspend fun getLatestItems(): Response<LatestDTO>

    @GET(FLASH_SALE_URL)
    suspend fun getFlashSaleItems(): Response<FlashSaleDTO>

    companion object {
        const val LATEST_URL = "/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7/"
        const val FLASH_SALE_URL = "/v3/a9ceeb6e-416d-4352-bde6-2203416576ac/"
    }
}