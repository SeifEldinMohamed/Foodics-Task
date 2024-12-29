package com.example.foodicstask.data.mapper.cart

import com.example.foodicstask.data.data_sources.local.room.entities.CartItemEntity
import com.example.foodicstask.domain.model.CartItemDomainModel

fun CartItemEntity.toCartItemDomainModel(): CartItemDomainModel {
    return CartItemDomainModel(
        id,
        name,
        price,
        quantity
    )
}