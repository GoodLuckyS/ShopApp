package com.example.shopapp.data.remote.entity

import com.example.shopapp.data.DataMapper
import com.example.shopapp.data.mapToDomain
import com.example.shopapp.domain.shop.ProductsAndCategory

data class LatestDTO(
    val latest: List<ProductDTO> = listOf(),
) : DataMapper<LatestDTO, ProductsAndCategory> {
    override fun LatestDTO.mapToDomain()= ProductsAndCategory(
        title = "Latest",
        productsList = this.latest.map { it.mapToDomain() }
    )
}
