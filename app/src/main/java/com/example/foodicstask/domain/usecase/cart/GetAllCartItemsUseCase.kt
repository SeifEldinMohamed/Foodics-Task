package com.example.foodicstask.domain.usecase.cart

import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetAllCartItemsUseCase(private val repository: CartRepository) {
    operator fun invoke(): Flow<List<CartItemDomainModel>> {
        return repository.getAllCartItems()
    }
}