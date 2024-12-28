package com.example.foodicstask.data.data_sources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(category: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItems(foodItem: List<FoodItemEntity>)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM food_items")
    suspend fun getAllFoodItems(): List<FoodItemEntity>

    @Query("SELECT * FROM food_items WHERE categoryid = :selectedCategoryId")
    suspend fun filterFoodsByCategory(selectedCategoryId: Int): List<FoodItemEntity>

    // Find rows where the name contains the substring provided in :name ( lower to make search case-insensitive)
    @Query("SELECT * FROM food_items WHERE LOWER(name) LIKE '%' || Lower(:name) || '%'")
    suspend fun searchFoodsByName(name: String): List<FoodItemEntity>

}
