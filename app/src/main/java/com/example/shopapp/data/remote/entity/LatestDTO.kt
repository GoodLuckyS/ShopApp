package com.example.shopapp.data.remote.entity

data class SoapDTO(
     val latest : List<ShopItemDTO> = listOf()
)

fun SoapDTO.toDomain() = this.latest.map {
    it.toDomain()
}