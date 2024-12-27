package com.example.foodicstask.presentation.mapper

import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel

fun CategoryDomainModel.toCategoryUiModel(): CategoryUiModel {
    return CategoryUiModel(
        id = this.id,
        name = this.name
    )
}