package com.example.foodicstask.data.mapper.food

import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.domain.model.FoodItemDomainModel

fun FoodItemEntity.toFoodItemDomainModel(): FoodItemDomainModel {
    return FoodItemDomainModel(
        id,
        name,
        description,
        image,
        price,
        categoryId
    )
}
