package com.example.foodicstask.data.mapper

import com.example.foodicstask.data.data_sources.remote.ktor.model.food_list.FoodItemDataModel
import com.example.foodicstask.domain.model.FoodItemDomainModel

fun FoodItemDataModel.toMovieDomainModel(): FoodItemDomainModel {
    return FoodItemDomainModel(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.image,
        price = this.price,
        categoryDomainModel = this.categoryDataModel.toCategoryDomainModel()
    )
}
