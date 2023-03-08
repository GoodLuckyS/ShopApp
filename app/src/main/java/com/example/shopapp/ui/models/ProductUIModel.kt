package com.example.shopapp.ui.models

import com.example.shopapp.domain.shop.models.Product
import com.example.shopapp.ui.base.BaseUIModel

data class ProductUIModel(
    val category: String = "",
    val name: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val discount: String = "",
    override val id: String = "",
) : BaseUIModel<String> {

    override fun toString(): String =this.name

}

fun Product.mapToUI() = ProductUIModel(
    category = category,
    name = name,
    price = price.toString(),
    imageUrl = imageUrl,
    discount = discount.toString(),
    id = id
)

