package com.example.foodicstask.data.mapper.category

import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.domain.model.CategoryDomainModel

fun CategoryEntity.toCategoryDomainModel(): CategoryDomainModel {
    return CategoryDomainModel(
        id = id,
        name = name
    )
}
