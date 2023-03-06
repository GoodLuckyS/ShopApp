package com.example.shopapp.presentation.entity

import com.example.shopapp.domain.shop.Product
import com.example.shopapp.presentation.base.BaseUIModel

data class ProductUIModel(
    val category : String = "",
    val name : String = "",
    val price : String="",
    val imageUrl : String = "",
    val discount : String = "",
    override val id: Int = -1,
):BaseUIModel<Int>

fun Product.mapToUI() = ProductUIModel(
    category = category, name,price= price.toString(), imageUrl, discount = discount.toString()
)

