package com.example.shopapp.domain.shop

data class ShopList(
    val latest : List<Product>,
    val flashSale : List<Product>,
)
