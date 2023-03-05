package com.example.shopapp.data.remote.entity

import com.example.shopapp.domain.shop.ShopItem
import com.google.gson.annotations.SerializedName

data class ShopItemDTO(
    @SerializedName("category") val category: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: Float? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
)

fun ShopItemDTO.toDomain() = ShopItem(
    category = category ?: "",
    name = name ?: "",
    price = price?.toInt() ?: 0,
    imageUrl = imageUrl ?: ""
)

fun List<ShopItemDTO>?.toDomain() = this?.map {
    it.toDomain()
}