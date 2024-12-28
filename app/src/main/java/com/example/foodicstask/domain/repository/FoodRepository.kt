package com.example.foodicstask.domain.repository

import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.domain.model.FoodItemDomainModel

interface FoodRepository {
    suspend fun fetchFoodList(): List<FoodItemDomainModel>
    suspend fun fetchCategoryList(): List<CategoryDomainModel>
    suspend fun filteredFoodListByCategory(selectedCategoryId: Int): List<FoodItemDomainModel>
    suspend fun searchFoodListByName(name:String): List<FoodItemDomainModel>
}