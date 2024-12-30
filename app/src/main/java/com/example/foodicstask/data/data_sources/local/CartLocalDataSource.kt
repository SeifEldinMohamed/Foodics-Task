package com.example.foodicstask.data.data_sources.local

import com.example.foodicstask.data.data_sources.local.room.CartDao
import com.example.foodicstask.data.data_sources.local.room.entities.CartItemEntity
import com.example.foodicstask.data.mapper.exceptions.toCustomDatabaseExceptionDomainModel
import kotlinx.coroutines.flow.Flow

class CartLocalDataSource(
    private val cartDao: CartDao,
) {

    fun getAllCartItems(): Flow<List<CartItemEntity>> {
        try {
            return cartDao.getAllCartItems()
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun insertOrUpdateCartItem(cartItem: CartItemEntity) {
        try {
            cartDao.insertOrUpdateCartItem(cartItem)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun clearCart() {
        try {
            cartDao.clearCart()
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }
}
