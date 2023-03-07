package com.example.shopapp.presentation.models

import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.example.shopapp.presentation.base.BaseUIModel

data class ProductAndCategoryUI(
    val title: String = "",
    val list : List<ProductUIModel> = emptyList(),
    override val id: String = ""
) : BaseUIModel<String>

fun ProductsAndCategory.mapToUI() = ProductAndCategoryUI(
    title = category.string,
    list = productsList.map { it.mapToUI() },
    id = id
)




