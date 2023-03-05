package com.example.shopapp.domain.shop

data class ShopList(
    val latest : List<ShopItem>,
    val flashSale : List<ShopItem>,
)
