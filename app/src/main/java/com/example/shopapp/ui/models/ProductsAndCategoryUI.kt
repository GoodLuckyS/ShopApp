package com.example.shopapp.ui.models

import com.example.shopapp.domain.shop.models.ProductsAndCategory
import com.example.shopapp.ui.base.BaseUIModel

data class ProductsAndCategoryUI(
    val title: String = "",
    val list : List<ProductUIModel> = emptyList(),
    override val id: String = ""
) : BaseUIModel<String>

fun ProductsAndCategory.mapToUI() = ProductsAndCategoryUI(
    title = category.string,
    list = productsList.map { it.mapToUI() },
    id = id
)

fun List<ProductsAndCategory>.mapToUI()= this.map {
    it.mapToUI()
}

fun List<ProductsAndCategoryUI>.mapToDropDownAdapter() : List<ProductUIModel> {
    val temp = mutableListOf<ProductUIModel>()
    this.forEach {
        temp.addAll(it.list)
    }
    return temp
}




