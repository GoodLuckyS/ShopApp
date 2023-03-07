package com.example.shopapp.data.remote.models

import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.data.utils.mapToDomain
import com.example.shopapp.domain.shop.models.Category
import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.google.gson.annotations.SerializedName
import java.util.*

data class FlashSaleDTO(
    @SerializedName("flash_sale") val flashSale: List<ProductFlashSaleDTO> = listOf(),
) : DataMapper<FlashSaleDTO, ProductsAndCategory> {
    override fun FlashSaleDTO.mapToDomain()= ProductsAndCategory(
        id = UUID.randomUUID().toString(),
        category = Category.FlashSale,
        productsList = this.flashSale.map { it.mapToDomain() }
    )
}
