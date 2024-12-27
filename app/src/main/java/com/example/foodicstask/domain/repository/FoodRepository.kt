package com.example.foodicstask.domain.repository

import com.example.foodicstask.domain.model.FoodItemDomainModel

interface FoodRepository {
    suspend fun fetchFoodList(): List<FoodItemDomainModel>
}