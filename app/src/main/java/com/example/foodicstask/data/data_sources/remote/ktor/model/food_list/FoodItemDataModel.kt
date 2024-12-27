package com.example.foodicstask.data.data_sources.remote.ktor.model.food_list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodItemDataModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Float,
    @SerialName("category")
    val categoryDataModel: CategoryDataModel,
)