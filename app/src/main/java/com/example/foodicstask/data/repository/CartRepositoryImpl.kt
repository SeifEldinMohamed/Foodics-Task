package com.example.foodicstask.data.repository

import com.example.foodicstask.data.data_sources.local.CartLocalDataSource
import com.example.foodicstask.data.mapper.cart.toCartItemDomainModel
import com.example.foodicstask.data.mapper.cart.toCartItemEntity
import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(private val cartLocalDataSource: CartLocalDataSource) : CartRepository {

    override fun getAllCartItems(): Flow<List<CartItemDomainModel>> {
        return cartLocalDataSource.getAllCartItems().map { it.map { cartItemEntity -> cartItemEntity.toCartItemDomainModel() } }
    }

    override suspend fun insertOrUpdateCartItem(cartItem: CartItemDomainModel) {
        cartLocalDataSource.insertOrUpdateCartItem(cartItem.toCartItemEntity())
    }

    override suspend fun clearCart() {
        cartLocalDataSource.clearCart()
    }
}