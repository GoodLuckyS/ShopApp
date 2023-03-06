package com.example.shopapp.data.remote.entity

import com.example.shopapp.data.DataMapper
import com.example.shopapp.domain.shop.Product
import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("category") val category: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: Float? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
) : DataMapper<ProductDTO, Product> {
    override fun ProductDTO.mapToDomain() = Product(
        category = category ?: "",
        name = name ?: "",
        price = (price ?: 0) as Float,
        imageUrl = imageUrl ?: "",
    )
}

