package com.example.foodicstask.data.data_sources.local

import com.example.foodicstask.data.data_sources.local.room.CartDao
import com.example.foodicstask.data.data_sources.local.room.entities.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartLocalDataSource(
    private val cartDao: CartDao
) {

    fun getAllCartItems(): Flow<List<CartItemEntity>> {
        return cartDao.getAllCartItems()
    }

    suspend fun insertOrUpdateCartItem(cartItem: CartItemEntity) {
        cartDao.insertOrUpdateCartItem(cartItem)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

}
