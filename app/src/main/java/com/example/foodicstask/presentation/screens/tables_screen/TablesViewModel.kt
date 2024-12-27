package com.example.foodicstask.presentation.screens.tables_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import com.example.foodicstask.presentation.mapper.toCategoryUiModel
import com.example.foodicstask.presentation.mapper.toCustomApiExceptionUiModel
import com.example.foodicstask.presentation.mapper.toCustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.mapper.toFoodItemUIModel
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.utils.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TablesViewModel(
    private val fetchFoodListUseCase: FetchFoodListUseCase,
    private val fetchCategoriesListUseCase: FetchCategoriesListUseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _tablesScreenUiState: MutableStateFlow<TablesScreenUiState> =
        MutableStateFlow(TablesScreenUiState.Loading(isLoading = false))
    val tablesScreenUiState: StateFlow<TablesScreenUiState> = _tablesScreenUiState.asStateFlow()

    fun requestFoodList() {
        _tablesScreenUiState.value = TablesScreenUiState.Loading(isLoading = true)
        viewModelScope.launch(dispatcher.io) {
            try {
                val foodListDomainModelDeferred = async { fetchFoodListUseCase() }
                val categoryListDomainDeferred = async { fetchCategoriesListUseCase() }

                val foodListDomainModel = foodListDomainModelDeferred.await()
                val categoryListDomainModel = categoryListDomainDeferred.await()

                val foodListUiModel = foodListDomainModel.map { it.toFoodItemUIModel() }
                val categoryListUiModel = categoryListDomainModel.map { it.toCategoryUiModel() }

                _tablesScreenUiState.value = TablesScreenUiState.FetchedTableData(
                    foodList = foodListUiModel,
                    categoryList = categoryListUiModel
                )

            } catch (e: Exception) {
                handleCustomException(e)
            }
        }
    }

    private fun handleCustomException(exception: Throwable) {
        when (val customException = exception as? CustomExceptionDomainModel) {
            is CustomExceptionDomainModel.Api -> {
                val apiExceptionUiModel =
                    customException.apiException.toCustomApiExceptionUiModel()
                _tablesScreenUiState.value = TablesScreenUiState.ApiError(apiExceptionUiModel)
            }

            is CustomExceptionDomainModel.Database -> {
                val databaseExceptionUiModel =
                    customException.databaseException.toCustomDatabaseExceptionUiModel()
                _tablesScreenUiState.value =
                    TablesScreenUiState.DatabaseError(databaseExceptionUiModel)
            }

            else -> {
                _tablesScreenUiState.value = TablesScreenUiState.ApiError(
                    customApiErrorExceptionUiModel = CustomApiExceptionUiModel.Unknown
                )
            }
        }
    }
}



