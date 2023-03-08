package com.example.shopapp.domain.shop.models

data class ProductAndDetails(
    val name: String,
    val description: String,
    val rating: Float,
    val numbersOfReviews : Int,
    val price: Float,
    val colors: List<String>,
    val imagesUrls: List<String>,
    val id: String = UNDEF_ID,
) {
    companion object {
        const val UNDEF_ID: String = ""
    }
}