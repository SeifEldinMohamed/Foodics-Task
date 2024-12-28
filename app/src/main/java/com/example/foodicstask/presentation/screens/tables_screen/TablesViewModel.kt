package com.example.foodicstask.presentation.screens.tables_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import com.example.foodicstask.domain.usecase.FilteredFoodListByCategoryUseCase
import com.example.foodicstask.domain.usecase.SearchFoodListByNameUseCase
import com.example.foodicstask.presentation.mapper.toCategoryUiModel
import com.example.foodicstask.presentation.mapper.toCustomApiExceptionUiModel
import com.example.foodicstask.presentation.mapper.toCustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.mapper.toFoodItemUIModel
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.utils.DispatcherProvider
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class TablesViewModel(
    private val fetchFoodListUseCase: FetchFoodListUseCase,
    private val filteredFoodListByCategoryUseCase: FilteredFoodListByCategoryUseCase,
    private val searchFoodListByNameUseCase: SearchFoodListByNameUseCase,
    private val fetchCategoriesListUseCase: FetchCategoriesListUseCase,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _tablesScreenUiState: MutableStateFlow<TablesScreenUiState> = MutableStateFlow(TablesScreenUiState.LoadingScreen(isLoading = false))
    val tablesScreenUiState: StateFlow<TablesScreenUiState> = _tablesScreenUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var originalFoodList: List<FoodItemUiModel> = emptyList()
    private var categoriesList: List<CategoryUiModel> = emptyList<CategoryUiModel>()

    fun requestTablesScreenData() {
        _tablesScreenUiState.value = TablesScreenUiState.LoadingScreen(isLoading = true)
        viewModelScope.launch(dispatcher.io) {
            try {
                val foodListDomainModelDeferred = async { fetchFoodListUseCase() }
                val categoryListDomainDeferred = async { fetchCategoriesListUseCase() }

                val foodListDomainModel = foodListDomainModelDeferred.await()
                val categoryListDomainModel = categoryListDomainDeferred.await()

                val foodListUiModel = foodListDomainModel.map { it.toFoodItemUIModel() }
                val categoryListUiModel = categoryListDomainModel.map { it.toCategoryUiModel() }

                _tablesScreenUiState.value = TablesScreenUiState.LoadingScreen(isLoading = false)
                _tablesScreenUiState.value = TablesScreenUiState.FetchedTableData(
                    foodList = foodListUiModel,
                    categoryList = categoryListUiModel
                )
                originalFoodList = foodListUiModel
                categoriesList = categoryListUiModel

            } catch (e: Exception) {
                handleCustomException(e)
            }
        }
    }

    fun filterFoodsByCategory(selectedCategoryId: Int) {
        _tablesScreenUiState.value = TablesScreenUiState.LoadingFoodList(isLoading = true, categoriesList = categoriesList)
        viewModelScope.launch(dispatcher.io) {
        delay(1000L)
            try {
                val filteredFoodList = if (selectedCategoryId == 0){
                    fetchFoodListUseCase().map { it.toFoodItemUIModel() }
                } else {
                    filteredFoodListByCategoryUseCase(selectedCategoryId).map { it.toFoodItemUIModel() }
                }
                _tablesScreenUiState.value = TablesScreenUiState.LoadingFoodList(isLoading = false, categoriesList = categoriesList)
                _tablesScreenUiState.value = TablesScreenUiState.FilteredFoodsByCategory(filteredFoodList = filteredFoodList, categoriesList)
                originalFoodList = filteredFoodList
            } catch (e: Exception) {
                handleCustomException(e)
            }
        }
    }
    
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    @OptIn(FlowPreview::class)
    fun observeSearchQuery() {
        viewModelScope.launch(dispatcher.io) {
            _searchQuery
                .debounce(500) // Waits for 500 milliseconds of inactivity before emitting the latest query.
                .distinctUntilChanged() // Ensures the flow emits only new queries, ignoring repeated values.
                .collect { query ->
                    if (query.isEmpty()) {
                        _tablesScreenUiState.value = TablesScreenUiState.FilteredFoodsByCategory(filteredFoodList = originalFoodList, categoriesList)

                    } else {
                        try {
                            val searchedFoods = searchFoodListByNameUseCase(name = query)
                            if (searchedFoods.isEmpty()) {
                                _tablesScreenUiState.value = TablesScreenUiState.EmptySearchState
                            } else {
                                _tablesScreenUiState.value = TablesScreenUiState.SearchedFoodsByName(
                                    searchedFoodList = searchedFoods.map { it.toFoodItemUIModel() },
                                    categoriesList = categoriesList
                                )
                            }
                        } catch (e:Exception) {
                            handleCustomException(e)
                        }
                    }
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




