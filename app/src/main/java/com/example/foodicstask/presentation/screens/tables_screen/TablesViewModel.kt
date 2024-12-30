package com.example.foodicstask.presentation.screens.tables_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import com.example.foodicstask.domain.usecase.FilteredFoodListByCategoryUseCase
import com.example.foodicstask.domain.usecase.SearchFoodListByNameUseCase
import com.example.foodicstask.domain.usecase.cart.AddItemToCartUseCase
import com.example.foodicstask.domain.usecase.cart.ClearCartUseCase
import com.example.foodicstask.domain.usecase.cart.GetAllCartItemsUseCase
import com.example.foodicstask.presentation.mapper.cart.toCartItemDomainModel
import com.example.foodicstask.presentation.mapper.category.toCategoryUiModel
import com.example.foodicstask.presentation.mapper.exception.toCustomApiExceptionUiModel
import com.example.foodicstask.presentation.mapper.exception.toCustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.mapper.cart.toCartItemUiModel
import com.example.foodicstask.presentation.mapper.food.toFoodItemUIModel
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TablesViewModel(
    private val fetchFoodListUseCase: FetchFoodListUseCase,
    private val filteredFoodListByCategoryUseCase: FilteredFoodListByCategoryUseCase,
    private val searchFoodListByNameUseCase: SearchFoodListByNameUseCase,
    private val fetchCategoriesListUseCase: FetchCategoriesListUseCase,
    private val dispatcher: DispatcherProvider,
    private val getAllCartItemsUseCase: GetAllCartItemsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
) : ViewModel() {

    private val _tablesScreenUiState: MutableStateFlow<TablesScreenUiState> =
        MutableStateFlow(TablesScreenUiState.LoadingScreen(isLoading = false))
    val tablesScreenUiState: StateFlow<TablesScreenUiState> = _tablesScreenUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var originalFoodList: List<FoodItemUiModel> = emptyList()
    private var categoriesList: List<CategoryUiModel> = emptyList()


    private val handler = CoroutineExceptionHandler { _, exception ->
        handleCustomException(exception)
    }

    init {
        observeSearchQuery()
    }

    fun getCartItems(): Flow<List<CartItemUiModel>> {
        return getAllCartItemsUseCase().map { it.map { cartItemDomainModel -> cartItemDomainModel.toCartItemUiModel() } }
    }

    fun loadFoodAndCategoriesData() {
        _tablesScreenUiState.value = TablesScreenUiState.LoadingScreen(isLoading = true)
        viewModelScope.launch(dispatcher.io + handler) {
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
        _tablesScreenUiState.value =
            TablesScreenUiState.LoadingFoodList(isLoading = true, categoriesList = categoriesList)
        viewModelScope.launch(dispatcher.io) {
            delay(1000L)
            try {
                val filteredFoodList =
                    if (selectedCategoryId == 0) {
                        fetchFoodListUseCase().map { it.toFoodItemUIModel() }
                    } else {
                        filteredFoodListByCategoryUseCase(selectedCategoryId).map { it.toFoodItemUIModel() }
                    }
                _tablesScreenUiState.value = TablesScreenUiState.LoadingFoodList(
                    isLoading = false,
                    categoriesList = categoriesList
                )
                if (filteredFoodList.isEmpty()) {
                    _tablesScreenUiState.value = TablesScreenUiState.EmptyState
                } else {
                    _tablesScreenUiState.value = TablesScreenUiState.FilteredFoodsByCategory(
                        filteredFoodList = filteredFoodList,
                        categoriesList = categoriesList
                    )
                    originalFoodList = filteredFoodList
                }
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
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isEmpty()) {
                        _tablesScreenUiState.value = TablesScreenUiState.SearchedFoodsByName(
                            searchedFoodList = originalFoodList,
                            categoriesList = categoriesList
                        )
                    } else {
                        try {
                            val searchedFoods = searchFoodListByNameUseCase(name = query)
                            if (searchedFoods.isEmpty()) {
                                _tablesScreenUiState.value = TablesScreenUiState.EmptyState
                            } else {
                                _tablesScreenUiState.value =
                                    TablesScreenUiState.SearchedFoodsByName(
                                        searchedFoodList = searchedFoods.map { foodItemDomainModel ->
                                            foodItemDomainModel.toFoodItemUIModel()
                                        },
                                        categoriesList = categoriesList
                                    )
                            }
                        } catch (e: Exception) {
                            handleCustomException(e)
                        }
                    }
                }
        }
    }

    fun addItemToCart(foodItem: FoodItemUiModel) {
        viewModelScope.launch(dispatcher.io) {
            getCartItems().firstOrNull()?.let { currentCartItems ->
                val existingItem = currentCartItems.find { it.id == foodItem.id }

                val cartItem = existingItem?.copy(quantity = existingItem.quantity + 1)
                    ?.toCartItemDomainModel()
                    ?: CartItemDomainModel(
                        id = foodItem.id,
                        name = foodItem.name,
                        price = foodItem.price,
                        quantity = 1,
                    )

                addItemToCartUseCase(cartItem)
            }
        }
    }


    fun clearCart() {
        viewModelScope.launch(dispatcher.io) {
            clearCartUseCase()
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




