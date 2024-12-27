package com.example.foodicstask.data.repository

import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import com.example.foodicstask.data.mapper.toCategoryDomainModel
import com.example.foodicstask.data.mapper.toMovieDomainModel
import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.domain.model.FoodItemDomainModel
import com.example.foodicstask.domain.repository.FoodRepository

class FoodRepositoryImpl(
    private val foodRemoteDataSource: FoodRemoteDataSource,
) : FoodRepository {
    override suspend fun fetchFoodList(): List<FoodItemDomainModel> {
        return try {
            foodRemoteDataSource.fetchFoodList().map { it.toMovieDomainModel() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchCategoryList(): List<CategoryDomainModel> {
        return try {
            foodRemoteDataSource.fetchCategoryList().map { it.toCategoryDomainModel() }
        } catch (e: Exception) {
            throw e
        }
    }
}