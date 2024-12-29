package com.example.foodicstask.presentation.mapper.cart

import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel

fun CartItemUiModel.toCartItemDomainModel(): CartItemDomainModel {
    return CartItemDomainModel(
        id,
        name,
        price,
        quantity
    )
}