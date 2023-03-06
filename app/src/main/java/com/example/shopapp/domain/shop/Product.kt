package com.example.shopapp.domain.shop

data class Product(
    val category : String,
    val name : String,
    val price : Float,
    val imageUrl : String,
    val discount : Int = UNDEF_FIELD
) {
    companion object {
        const val UNDEF_FIELD:Int = -1
    }
}