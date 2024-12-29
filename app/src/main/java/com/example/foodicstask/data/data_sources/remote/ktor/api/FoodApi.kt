package com.example.foodicstask.data.data_sources.remote.ktor.api

import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.CategoryDataModel
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.FoodItemDataModel

interface FoodApi {
    suspend fun fetchFoodList(): List<FoodItemDataModel>
    suspend fun fetchCategoryList(): List<CategoryDataModel>
}