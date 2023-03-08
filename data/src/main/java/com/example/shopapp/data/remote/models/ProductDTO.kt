package com.example.shopapp.data.remote.models

import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.domain.shop.models.Product
import com.google.gson.annotations.SerializedName
import java.util.*

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
        id = UUID.randomUUID().toString()
    )
}

