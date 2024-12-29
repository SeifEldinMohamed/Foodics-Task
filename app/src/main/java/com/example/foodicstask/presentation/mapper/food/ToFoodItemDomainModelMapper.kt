package com.example.foodicstask.presentation.mapper.food

import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel

fun FoodItemUiModel.toFoodItemDomainModel(): FoodItemDomainModel {
    return FoodItemDomainModel(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        price = this.price,
        categoryId = this.categoryId,
        countInCart = this.countInCart
    )
}

