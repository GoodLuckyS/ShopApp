package com.example.shopapp.presentation.models

import com.example.shopapp.domain.shop.models.ProductAndDetails
import com.example.shopapp.presentation.base.BaseUIModel

data class ProductAndDetailsUI(
    val name: String,
    val description: String,
    val rating: String,
    val numbersOfReviews: String,
    val price: String,
    val colors: List<SelectorUI.Color>,
    val imageUrls: List<SelectorUI.Image>,
    override val id: String,
) : BaseUIModel<String>

fun ProductAndDetails.mapToUI() = ProductAndDetailsUI(
    name =name,
    description = description,
    rating = rating.toString(),
    numbersOfReviews = numbersOfReviews.toString(),
    price = price.toString(),
    colors = colors.map {  it.toSelectorUIColor() },
    imageUrls = imagesUrls.map { it.toSelectorUIImage() },
    id = id
)
