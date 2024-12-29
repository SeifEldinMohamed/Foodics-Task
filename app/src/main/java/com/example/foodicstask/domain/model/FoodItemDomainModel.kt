package com.example.foodicstask.domain.model

data class FoodItemDomainModel(
    val id: Int,
    val name: String,
    val description:String,
    val image:String,
    val price: Float,
    val categoryId:Int,
    val countInCart:Int = 0
)
