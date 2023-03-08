package com.example.shopapp.domain.shop.models

data class Product(
    val category: String,
    val name: String,
    val price: Float,
    val imageUrl: String,
    val discount: Int = UNDEF_FIELD,
    val id: String = UNDEF_ID,
) {
    companion object {
        const val UNDEF_ID: String = ""
        const val UNDEF_FIELD: Int = -1
    }
}