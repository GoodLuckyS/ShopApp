package com.example.shopapp.data.remote.models

import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.data.utils.mapToDomain
import com.example.shopapp.domain.shop.models.Category
import com.example.shopapp.domain.shop.models.ProductsAndCategory
import java.util.*

data class LatestDTO(
    val latest: List<ProductDTO> = listOf(),
) : DataMapper<LatestDTO, ProductsAndCategory> {
    override fun LatestDTO.mapToDomain() = ProductsAndCategory(
        id = UUID.randomUUID().toString(),
        category = Category.Latest,
        productsList = this.latest.map { it.mapToDomain() }
    )
}
