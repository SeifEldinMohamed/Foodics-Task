package com.example.foodicstask.data.data_sources.local

import com.example.foodicstask.data.data_sources.local.room.FoodDao
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.data.mapper.exceptions.toCustomDatabaseExceptionDomainModel

class FoodLocalDataSource(
    private val foodDao: FoodDao,
) {
    suspend fun insertCategories(categories: List<CategoryEntity>) {
        try {
            foodDao.insertCategories(categories)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun insertFoodItems(foodItems: List<FoodItemEntity>) {
        try {
            foodDao.insertFoodItems(foodItems)
        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun getAllCategories(): List<CategoryEntity> {
        try {
            return foodDao.getAllCategories()

        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun getAllFoodItems(): List<FoodItemEntity> {
        try {
            return foodDao.getAllFoodItems()

        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }

    }

    suspend fun filterFoodsByCategory(selectedCategoryId: Int): List<FoodItemEntity> {
        try {
            return foodDao.filterFoodsByCategory(selectedCategoryId)

        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }

    suspend fun searchFoodsByName(name: String): List<FoodItemEntity> {
        try {
            return foodDao.searchFoodsByName(name)

        } catch (e: Exception) {
            throw e.toCustomDatabaseExceptionDomainModel()
        }
    }
}
