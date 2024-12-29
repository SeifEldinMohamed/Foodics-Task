package com.example.foodicstask.domain.repository

import com.example.foodicstask.domain.model.CartItemDomainModel
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllCartItems(): Flow<List<CartItemDomainModel>>
    suspend fun insertOrUpdateCartItem(cartItem: CartItemDomainModel)
    suspend fun clearCart()
}