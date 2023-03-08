package com.example.shopapp.domain.shop.models

data class ProductsAndCategory(
    val category: Category,
    val productsList : List<Product>,
    val id:String = ""
)