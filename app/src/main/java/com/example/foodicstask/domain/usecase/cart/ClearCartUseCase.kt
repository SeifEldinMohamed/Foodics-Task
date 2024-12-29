package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.repository.CartRepository

class ClearCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke() {
        repository.clearCart()
    }
}
