package com.example.foodicstask.data.data_sources.local

import com.example.foodicstask.data.data_sources.local.room.FoodDao
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity

class FoodLocalDataSource (
    private val foodDao: FoodDao
) {
    suspend fun insertCategories(categories: List<CategoryEntity>) {
        foodDao.insertCategories(categories)
    }
    suspend fun insertFoodItems(foodItems: List<FoodItemEntity>) {
        foodDao.insertFoodItems(foodItems)
    }
    suspend fun getAllCategories(): List<CategoryEntity> {
        return foodDao.getAllCategories()
    }
    suspend fun getAllFoodItems(): List<FoodItemEntity> {
        return foodDao.getAllFoodItems()
    }

}
