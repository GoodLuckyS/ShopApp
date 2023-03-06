package com.example.shopapp.domain.shop

import com.example.shopapp.domain.AppResponse

interface ShopRepository {

    suspend fun getAllProductsList() : AppResponse<List<ProductsAndCategory>>

   suspend fun getProductById(id:Int)

}