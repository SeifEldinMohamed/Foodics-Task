package com.example.foodicstask.data.mapper.food

import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.FoodItemDataModel

fun FoodItemDataModel.toFoodItemEntity(): FoodItemEntity {
    return FoodItemEntity(
        id,
        name,
        description,
        image,
        price,
        categoryDataModel.id
    )
}
