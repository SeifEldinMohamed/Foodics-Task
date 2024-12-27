package com.example.foodicstask.presentation.screens.tables_screen.model

data class FoodItemUiModel(
    val id: Int,
    val name: String,
    val description:String,
    val image:String,
    val price: Float,
    val categoryUiModel: CategoryUiModel
)
