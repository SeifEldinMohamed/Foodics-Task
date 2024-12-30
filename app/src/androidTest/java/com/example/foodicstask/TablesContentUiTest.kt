package com.example.foodicstask

import TablesContent
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.screens.tables_screen.TablesScreenUiState
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeCategoryListUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeFoodItemListUiModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TablesContentUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var tablesUiState: TablesScreenUiState
    private var searchQuery by mutableStateOf("")
    private lateinit var context: Context
    private lateinit var cartItems: List<CartItemUiModel>

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        searchQuery = ""
        cartItems = listOf()
        tablesUiState = TablesScreenUiState.FetchedTableData(
            categoryList = fakeCategoryListUiModel,
            foodList = fakeFoodItemListUiModel
        )
    }

    @Test
    fun testEmptyStateDisplayed() {
        // Arrange
        val emptyState = TablesScreenUiState.EmptyState

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = emptyState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Assert that the empty state section is displayed
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_empty_section))
            .assertIsDisplayed()
    }

    @Test
    fun testLoadingStateDisplayed() {
        // Arrange
        val loadingState = TablesScreenUiState.LoadingScreen(isLoading = true)

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = loadingState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Assert that shimmer effect is displayed
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_shimmer_categories_bar))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_shimmer_food_list))
            .assertIsDisplayed()
    }

    @Test
    fun testFoodListDisplayed() {
        // Arrange
        val fetchedDataState = TablesScreenUiState.FetchedTableData(
            categoryList = fakeCategoryListUiModel,
            foodList = fakeFoodItemListUiModel
        )

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = fetchedDataState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Assert: Verify that food items are displayed
        composeTestRule.onNodeWithTag(
            context.getString(
                R.string.test_tag_food_item,
                fakeFoodItemListUiModel.first().id
            )
        ).assertIsDisplayed()
    }

    @Test
    fun testSearchBarFunctionality() {
        // Arrange
        searchQuery = "Pizza"

        // Act: Set the composable
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = tablesUiState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = { query -> searchQuery = query },
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Act: Perform text input in the search bar
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_searchbar_textfield))
            .performTextInput(searchQuery)

        // Assert: Ensure that the search query is updated
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_searchbar_textfield))
            .assert(hasText(searchQuery))
    }

    @Test
    fun testCategorySelection() {
        // Arrange
        val categoryId = 1

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = tablesUiState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = { selectedCategoryId ->
                    assert(selectedCategoryId == categoryId)
                },
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Act: Select category
        composeTestRule.onNodeWithTag(
            context.getString(
                R.string.test_tag_category_item,
                categoryId
            )
        ).performClick()
    }

    @Test
    fun testErrorStateDisplayed() {
        // Arrange
        val errorState =
            TablesScreenUiState.ApiError(customApiErrorExceptionUiModel = CustomApiExceptionUiModel.Network)

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = errorState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = {},
                onClearCart = {}
            )
        }

        // Assert: Verify that error section and retry button are displayed
        composeTestRule.onNodeWithTag(context.getString(R.string.test_tag_error_section))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.retry)).assertIsDisplayed()
    }

    @Test
    fun testFoodCardClickTriggersCallback() {
        // Arrange
        var clickedFoodItem: FoodItemUiModel? = null

        // Act
        composeTestRule.setContent {
            TablesContent(
                tablesUiState = tablesUiState,
                searchQuery = searchQuery,
                cartItems = cartItems,
                gridCellsCount = 2,
                onQueryChanged = {},
                onCategorySelected = {},
                onRefreshButtonClicked = {},
                onFoodCardClick = { foodItem -> clickedFoodItem = foodItem },
                onClearCart = {}
            )
        }

        // Act: Perform click on the first food item
        composeTestRule.onNodeWithTag(
            context.getString(
                R.string.test_tag_food_item,
                fakeFoodItemListUiModel.first().id
            )
        ).performClick()

        // Assert: Verify that food item click callback is triggered with correct food item
        assert(clickedFoodItem == fakeFoodItemListUiModel.first())
    }
}
