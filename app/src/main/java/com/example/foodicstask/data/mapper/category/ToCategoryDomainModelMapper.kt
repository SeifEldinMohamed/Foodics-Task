package com.example.foodicstask.data.mapper.category

import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.food_list.CategoryDataModel
import com.example.foodicstask.domain.model.CategoryDomainModel

fun CategoryDataModel.toCategoryDomainModel(): CategoryDomainModel {
    return CategoryDomainModel(
        id = id,
        name = name
    )
}

fun CategoryEntity.toCategoryDomainModel(): CategoryDomainModel {
    return CategoryDomainModel(
        id = id,
        name = name
    )
}
