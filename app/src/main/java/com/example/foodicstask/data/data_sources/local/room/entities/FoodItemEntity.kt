package com.example.foodicstask.data.data_sources.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Float,
    val categoryId:Int
)
