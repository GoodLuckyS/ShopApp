package com.example.shopapp.data.remote.models

import com.example.shopapp.data.utils.DataMapper
import com.example.shopapp.domain.shop.models.ProductAndDetails
import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductAndDetailsDTO(
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("rating") var rating: Float? = null,
    @SerializedName("number_of_reviews") var numberOfReviews: Int? = null,
    @SerializedName("price") var price: Float? = null,
    @SerializedName("colors") var colors: List<String> = listOf(),
    @SerializedName("image_urls") var imageUrls: List<String> = listOf(),
) : DataMapper<ProductAndDetailsDTO, ProductAndDetails> {
    override fun ProductAndDetailsDTO.mapToDomain(): ProductAndDetails =
        ProductAndDetails(
            name = name ?: "",
            description = description ?: "",
            rating = (rating ?: 0) as Float,
            numbersOfReviews = numberOfReviews ?: 0,
            price = (price ?: 0) as Float,
            colors = colors,
            imagesUrls = imageUrls
        )
}