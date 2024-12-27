package com.example.foodicstask.presentation.screens.tables_screen

import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.model.CustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel


sealed class TablesScreenUiState{
    data object EmptyState: TablesScreenUiState()
    data class Loading(val isLoading:Boolean): TablesScreenUiState()
    data class FetchedTableData(val foodList: List<FoodItemUiModel>, val categoryList: List<CategoryUiModel>): TablesScreenUiState()
    data class ApiError(val customApiErrorExceptionUiModel: CustomApiExceptionUiModel): TablesScreenUiState()
    data class DatabaseError(val customDatabaseExceptionUiModel: CustomDatabaseExceptionUiModel): TablesScreenUiState()
}
