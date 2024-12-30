package com.example.foodicstask.presentation.screens.tables

import app.cash.turbine.test
import com.example.foodicstask.domain.fake_data.fakeCategoriesDomainModelList
import com.example.foodicstask.domain.fake_data.fakeFoodItemsDomainModelList
import com.example.foodicstask.domain.fake_data.fakeSearchedFoodItemsList
import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomApiExceptionDomainModel
import com.example.foodicstask.domain.model.exceptions.CustomExceptionDomainModel
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import com.example.foodicstask.domain.usecase.FilteredFoodListByCategoryUseCase
import com.example.foodicstask.domain.usecase.SearchFoodListByNameUseCase
import com.example.foodicstask.domain.usecase.cart.AddItemToCartUseCase
import com.example.foodicstask.domain.usecase.cart.ClearCartUseCase
import com.example.foodicstask.domain.usecase.cart.GetAllCartItemsUseCase
import com.example.foodicstask.presentation.fake_data.fakeFoodItemUiModel
import com.example.foodicstask.presentation.mapper.category.toCategoryUiModel
import com.example.foodicstask.presentation.mapper.food.toFoodItemUIModel
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.TablesScreenUiState
import com.example.foodicstask.presentation.screens.tables_screen.TablesViewModel
import com.example.foodicstask.presentation.utils.MainCoroutineRule
import com.example.foodicstask.presentation.utils.TestDispatchersImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TablesViewModelTest {

    private lateinit var tablesViewModel: TablesViewModel
    private lateinit var fetchFoodListUseCase: FetchFoodListUseCase
    private lateinit var filteredFoodListByCategoryUseCase: FilteredFoodListByCategoryUseCase
    private lateinit var searchFoodListByNameUseCase: SearchFoodListByNameUseCase
    private lateinit var fetchCategoriesListUseCase: FetchCategoriesListUseCase
    private lateinit var dispatcher: TestDispatchersImpl
    private lateinit var addItemToCartUseCase: AddItemToCartUseCase
    private lateinit var clearCartUseCase: ClearCartUseCase
    private lateinit var getAllCartItemsUseCase: GetAllCartItemsUseCase

    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()

    @Before
    fun setup() {
        dispatcher = TestDispatchersImpl()
        fetchFoodListUseCase = mockk()
        filteredFoodListByCategoryUseCase = mockk()
        searchFoodListByNameUseCase = mockk()
        fetchCategoriesListUseCase = mockk()
        getAllCartItemsUseCase = mockk()
        clearCartUseCase = mockk()
        addItemToCartUseCase = mockk()

        tablesViewModel = TablesViewModel(
            fetchFoodListUseCase,
            filteredFoodListByCategoryUseCase,
            searchFoodListByNameUseCase,
            fetchCategoriesListUseCase,
            dispatcher,
            getAllCartItemsUseCase,
            addItemToCartUseCase,
            clearCartUseCase
        )
    }

    /**
     * Test Case: Request Tables Screen Data
     * Given: Successful responses from both food list and categories list use cases.
     * When: `requestTablesScreenData()` is called.
     * Then: It should emit the correct FetchedTableData state.
     */
    @Test
    fun `requestTablesScreenData, given successful responses, should emit FetchedTableData`() =
        runTest {
            // Given
            val foodList = fakeFoodItemsDomainModelList
            val categoriesList = fakeCategoriesDomainModelList
            coEvery { fetchFoodListUseCase() } returns foodList
            coEvery { fetchCategoriesListUseCase() } returns categoriesList

            val expectedState = TablesScreenUiState.FetchedTableData(
                foodList = foodList.map { it.toFoodItemUIModel() },
                categoryList = categoriesList.map { it.toCategoryUiModel() }
            )

            // When
            tablesViewModel.loadFoodAndCategoriesData()

            // Then
            tablesViewModel.tablesScreenUiState.test {
                assertEquals(TablesScreenUiState.LoadingScreen(isLoading = true), awaitItem())
                advanceUntilIdle()
                assertEquals(TablesScreenUiState.LoadingScreen(isLoading = false), awaitItem())
                assertEquals(expectedState, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    /**
     * Test Case: Filter Foods By Category
     * Given: Successful response from the filtered food list use case.
     * When: `filterFoodsByCategory()` is called with a categoryId.
     * Then: It should emit FilteredFoodsByCategory with the correct filtered list.
     */
    @Test
    fun `filterFoodsByCategory, given successful response, should emit FilteredFoodsByCategory`() =
        runTest {
            // Given
            val categoryId = 2
            val filteredFoods = fakeFoodItemsDomainModelList
            coEvery { filteredFoodListByCategoryUseCase(categoryId) } returns filteredFoods

            val expectedState = TablesScreenUiState.FilteredFoodsByCategory(
                filteredFoodList = filteredFoods.map { it.toFoodItemUIModel() },
                categoriesList = emptyList()
            )

            // When
            tablesViewModel.filterFoodsByCategory(categoryId)

            // Then
            tablesViewModel.tablesScreenUiState.test {
                assertEquals(
                    TablesScreenUiState.LoadingFoodList(
                        isLoading = true,
                        categoriesList = emptyList()
                    ), awaitItem()
                )
                assertEquals(
                    TablesScreenUiState.SearchedFoodsByName(
                        searchedFoodList = emptyList(),
                        categoriesList = emptyList()
                    ), awaitItem()
                )
                assertEquals(
                    TablesScreenUiState.LoadingFoodList(
                        isLoading = false,
                        categoriesList = emptyList()
                    ), awaitItem()
                )
                assertEquals(expectedState, awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

    /**
     * Test Case: Search Query Changed
     * Given: A valid query string.
     * When: `onSearchQueryChanged()` is called.
     * Then: It should update the searchQuery state.
     */
    @Test
    fun `onSearchQueryChanged, given a valid query, should update searchQuery state`() = runTest {
        // Given
        val query = "Pizza"

        // When
        tablesViewModel.onSearchQueryChanged(query)

        // Then
        tablesViewModel.searchQuery.test {
            assertEquals(query, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    /**
     * Test Case: Observe Search Query
     * Given: A search query and successful response from the use case.
     * When: `observeSearchQuery()` is called.
     * Then: It should emit the correct SearchedFoodsByName state.
     */
    @Test
    fun `observeSearchQuery, given successful search, should emit SearchedFoodsByName`() = runTest {
        // Given
        val query = "Burger"
        val searchedFoods = fakeSearchedFoodItemsList
        coEvery { searchFoodListByNameUseCase(query) } returns searchedFoods

        val expectedState = TablesScreenUiState.SearchedFoodsByName(
            searchedFoodList = searchedFoods.map { it.toFoodItemUIModel() },
            categoriesList = emptyList()
        )

        // When
        tablesViewModel.onSearchQueryChanged(query)
        tablesViewModel.observeSearchQuery()
        advanceUntilIdle()

        // Then
        tablesViewModel.tablesScreenUiState.test {
            assertEquals(expectedState, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    /**
     * Test Case: Handle Exception
     * Given: An exception occurs in any use case.
     * When: A method is called that triggers the exception.
     * Then: It should emit the correct ApiError state.
     */
    @Test
    fun `handleCustomException, given an API exception, should emit ApiError`() = runTest {
        // Given
        val exception =
            CustomExceptionDomainModel.Api(CustomApiExceptionDomainModel.UnknownExceptionDomainModel)
        coEvery { fetchFoodListUseCase() } throws exception

        // When
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            tablesViewModel.loadFoodAndCategoriesData()
        }

        // Then
        tablesViewModel.tablesScreenUiState.test {
            assertEquals(
                TablesScreenUiState.LoadingScreen(isLoading = true),
                awaitItem()
            )
            assertEquals(
                TablesScreenUiState.ApiError(
                    customApiErrorExceptionUiModel = CustomApiExceptionUiModel.Unknown
                ),
                awaitItem()
            )
            cancelAndConsumeRemainingEvents()
        }

    }

    /**
     * Test Case: Add Item to Cart - New Item
     * Given: A food item that does not already exist in the cart.
     * When: `addItemToCart()` is called with the new item.
     * Then: It should add the item to the cart with a quantity of 1.
     */
    @Test
    fun `addItemToCart adds a new item if it doesn't exist`() = runTest {
        // Given
        coEvery { getAllCartItemsUseCase() } returns flowOf(emptyList())
        val foodItem = fakeFoodItemUiModel
        coEvery { addItemToCartUseCase(any()) } just Runs

        // When
        tablesViewModel.addItemToCart(foodItem)
        advanceUntilIdle()

        // Then
        val expectedCartItem = CartItemDomainModel(
            id = foodItem.id,
            name = foodItem.name,
            price = foodItem.price,
            quantity = 1
        )
        coVerify { addItemToCartUseCase(expectedCartItem) }
    }

    /**
     * Test Case: Add Item to Cart - Existing Item
     * Given: A food item that already exists in the cart with a specific quantity.
     * When: `addItemToCart()` is called with the same item.
     * Then: It should update the item's quantity by incrementing it by 1.
     */
    @Test
    fun `addItemToCart updates the quantity if the item already exists`() = runTest {
        // Given
        val foodItem = fakeFoodItemUiModel

        val existingCartItem = CartItemDomainModel(
            id = foodItem.id,
            name = foodItem.name,
            price = foodItem.price,
            quantity = 1
        )
        val expectedCartItem = existingCartItem.copy(quantity = 2)

        coEvery { getAllCartItemsUseCase() } returns flowOf(listOf(existingCartItem))
        coEvery { addItemToCartUseCase(any()) } just Runs

        // When
        tablesViewModel.addItemToCart(foodItem)
        advanceUntilIdle()

        // Then
        coVerify { addItemToCartUseCase(expectedCartItem) }

    }

    /**
     * Test Case: Clear Cart
     * Given: A cart with items.
     * When: `clearCart()` is called.
     * Then: It should call the `clearCartUseCase` to clear all items from the cart.
     */
    @Test
    fun `clearCart calls the clearCartUseCase`() = runTest {
        coEvery { clearCartUseCase() } just Runs

        // When
        tablesViewModel.clearCart()
        advanceUntilIdle()

        // Then
        coVerify { clearCartUseCase() }
    }

}
