package com.example.foodicstask.data.data_sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity

@Database(
    entities = [FoodItemEntity::class, CategoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}