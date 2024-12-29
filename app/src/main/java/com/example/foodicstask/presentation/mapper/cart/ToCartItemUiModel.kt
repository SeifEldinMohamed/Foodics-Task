package com.example.foodicstask.presentation.mapper.cart

import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel

fun CartItemDomainModel.toCartItemUiModel(): CartItemUiModel {
    return CartItemUiModel(
        id,
        name,
        price,
        quantity
    )
}