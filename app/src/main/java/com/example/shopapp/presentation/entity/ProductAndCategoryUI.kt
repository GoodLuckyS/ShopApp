package com.example.shopapp.presentation.entity

import com.example.shopapp.domain.shop.ProductsAndCategory
import com.example.shopapp.presentation.base.BaseUIModel
import com.example.shopapp.presentation.utils.UIMapper

data class ProductAndCategoryUI(
    val title: String = "",
    val list : List<ProductUIModel> = emptyList(),
    override val id: Int = -1
) : BaseUIModel<Int>

fun ProductsAndCategory.mapToUI() = ProductAndCategoryUI(
    title = title,
    list = productsList.map { it.mapToUI() }
)




