package com.example.foodicstask.data.repository

import com.example.foodicstask.data.data_sources.local.FoodLocalDataSource
import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import com.example.foodicstask.data.mapper.category.toCategoryDomainModel
import com.example.foodicstask.data.mapper.category.toCategoryEntity
import com.example.foodicstask.data.mapper.food.toFoodItemDomainModel
import com.example.foodicstask.data.mapper.food.toFoodItemEntity
import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource,
    private val foodLocalDataSource: FoodLocalDataSource,
) : FoodRepository {
    override suspend fun fetchFoodList(): List<FoodItemDomainModel> {
        return try {
            val cachedFoodItems = foodLocalDataSource.getAllFoodItems()
            if (cachedFoodItems.isNotEmpty()){
                cachedFoodItems.map { it.toFoodItemDomainModel() }
            }
            else {
                val foodItemsDomainModel = foodRemoteDataSource.fetchFoodList()
                foodLocalDataSource.insertFoodItems(foodItemsDomainModel.map { it.toFoodItemEntity() })
                foodLocalDataSource.getAllFoodItems().map { it.toFoodItemDomainModel() }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchCategoryList(): List<CategoryDomainModel> {
        return try {
            val cachedCategories = foodLocalDataSource.getAllCategories()
            if (cachedCategories.isNotEmpty()) {
                cachedCategories.map { it.toCategoryDomainModel() }
            }
            else {
                val categoriesDomainModel = foodRemoteDataSource.fetchCategoryList()
                foodLocalDataSource.insertCategories(categoriesDomainModel.map { it.toCategoryEntity() })
                foodLocalDataSource.getAllCategories().map { it.toCategoryDomainModel() }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}