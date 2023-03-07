package com.example.shopapp.data.remote.models

import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.domain.shop.models.Product
import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductFlashSaleDTO (

    @SerializedName("category") val category: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: Float? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("discount") val discount : Int? = null
) : DataMapper<ProductFlashSaleDTO, Product> {
    override fun ProductFlashSaleDTO.mapToDomain(): Product =
        Product(
            category = category ?: "",
            name = name ?: "",
            price = (price ?: 0) as Float,
            imageUrl = imageUrl ?: "",
            discount = discount ?: Product.UNDEF_FIELD,
            id = UUID.randomUUID().toString()

        )
}