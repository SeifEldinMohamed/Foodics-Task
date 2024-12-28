package com.example.foodicstask.data.data_sources.local.room.converter

import androidx.room.TypeConverter
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.google.gson.Gson

class CategoryEntityConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromCategoryEntityToString(category: CategoryEntity): String {
        return gson.toJson(category)
    }

    @TypeConverter
    fun fromStringToCategoryEntity(json: String): CategoryEntity {
        return gson.fromJson(json, CategoryEntity::class.java)
    }
}