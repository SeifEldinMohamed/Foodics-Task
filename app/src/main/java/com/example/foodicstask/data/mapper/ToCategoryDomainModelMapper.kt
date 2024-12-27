package com.example.foodicstask.data.mapper

import com.example.foodicstask.data.data_sources.remote.ktor.model.food_list.CategoryDataModel
import com.example.foodicstask.domain.model.CategoryDomainModel

fun CategoryDataModel.toCategoryDomainModel(): CategoryDomainModel {
    return CategoryDomainModel(
        id = id,
        name = name
    )
}
