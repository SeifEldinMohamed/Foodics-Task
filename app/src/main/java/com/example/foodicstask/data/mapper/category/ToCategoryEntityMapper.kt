package com.example.foodicstask.data.mapper.category

import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.CategoryDataModel

fun CategoryDataModel.toCategoryEntity(): CategoryEntity = CategoryEntity(id, name)
