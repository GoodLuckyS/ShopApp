package com.example.shopapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class FlashSaleDTO(
    @SerializedName("flash_sale") val flashSale : List<ShopItemDTO> = listOf()
)

fun FlashSaleDTO.toDomain() = this.flashSale.map {
    it.toDomain()
}