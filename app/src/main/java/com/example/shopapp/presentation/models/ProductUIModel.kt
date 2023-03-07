package com.example.shopapp.presentation.models

import com.example.shopapp.domain.shop.models.Product
import com.example.shopapp.presentation.base.BaseUIModel

data class ProductUIModel(
    val category: String = "",
    val name: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val discount: String = "",
    override val id: String = "",
) : BaseUIModel<String>

fun Product.mapToUI() = ProductUIModel(
    category = category,
    name = name,
    price = price.toString(),
    imageUrl = imageUrl,
    discount = discount.toString(),
    id = id
)

