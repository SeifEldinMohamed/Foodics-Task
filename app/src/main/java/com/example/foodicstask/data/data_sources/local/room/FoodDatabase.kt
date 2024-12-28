package com.example.foodicstask.data.data_sources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodicstask.data.data_sources.local.room.converter.CategoryEntityConverter
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity

@Database(
    entities = [FoodItemEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CategoryEntityConverter::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}