package com.example.shopapp.data.remote.entity

import com.example.shopapp.data.DataMapper
import com.example.shopapp.data.mapToDomain
import com.example.shopapp.domain.shop.ProductsAndCategory
import com.google.gson.annotations.SerializedName

data class FlashSaleDTO(
    @SerializedName("flash_sale") val flashSale: List<ProductFlashSaleDTO> = listOf(),
) : DataMapper<FlashSaleDTO, ProductsAndCategory> {
    override fun FlashSaleDTO.mapToDomain()= ProductsAndCategory(
        title = "Flash sale",
        productsList = this.flashSale.map { it.mapToDomain() }
    )
}
