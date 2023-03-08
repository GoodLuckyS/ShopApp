package com.example.shopapp.domain.shop

import com.example.shopapp.domain.utils.AppResponse
import com.example.shopapp.domain.shop.models.ProductAndDetails
import com.example.shopapp.domain.shop.models.ProductsAndCategory

interface ShopRepository {

    suspend fun getAllProductsList() : AppResponse<List<ProductsAndCategory>>

   suspend fun getProductById(id:String): AppResponse<ProductAndDetails>

}