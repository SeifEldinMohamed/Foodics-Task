package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.repository.CartRepository

class AddItemToCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(cartItem: CartItemDomainModel) {
        repository.insertOrUpdateCartItem(cartItem)
    }
}
