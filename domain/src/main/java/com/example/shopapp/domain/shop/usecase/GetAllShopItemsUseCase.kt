package com.example.shopapp.domain.shop.usecase

import com.example.shopapp.domain.shop.ShopRepository
import javax.inject.Inject

class GetAllShopItemsUseCase @Inject constructor(private val repository: ShopRepository) {
    suspend operator fun invoke() = repository.getAllProductsList()
}