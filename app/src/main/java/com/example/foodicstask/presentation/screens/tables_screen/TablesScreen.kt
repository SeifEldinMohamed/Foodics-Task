import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodicstask.presentation.common_components.EmptySection
import com.example.foodicstask.presentation.common_components.ErrorSection
import com.example.foodicstask.presentation.common_components.SearchBar
import com.example.foodicstask.presentation.preview.DevicesPreviews
import com.example.foodicstask.presentation.screens.tables_screen.TablesScreenUiState
import com.example.foodicstask.presentation.screens.tables_screen.TablesViewModel
import com.example.foodicstask.presentation.screens.tables_screen.components.shimmer_animation.AnimateShimmerFoodList
import com.example.foodicstask.presentation.screens.tables_screen.components.CategoriesTabs
import com.example.foodicstask.presentation.screens.tables_screen.components.FoodListSection
import com.example.foodicstask.presentation.screens.tables_screen.components.ViewOrderButton
import com.example.foodicstask.presentation.screens.tables_screen.components.shimmer_animation.AnimateShimmerCategoriesBar
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.utils.getGridCellsCount
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme
import com.example.foodicstask.presentation.utils.toTwoDecimalPlaces
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TablesScreen(
    windowSize: WindowSizeClass,
) {
    val tablesViewModel = koinViewModel<TablesViewModel>()
    val tablesUiState = tablesViewModel.tablesScreenUiState.collectAsStateWithLifecycle()
    val searchQuery by tablesViewModel.searchQuery.collectAsStateWithLifecycle()
    val cartItems by tablesViewModel.getCartItems()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    LaunchedEffect(Unit) {
        tablesViewModel.loadFoodAndCategoriesData()
    }

    TablesContent(
        tablesUiState = tablesUiState.value,
        searchQuery = searchQuery,
        cartItems = cartItems,
        gridCellsCount = windowSize.getGridCellsCount(),
        onQueryChanged = { name ->
            tablesViewModel.onSearchQueryChanged(name)
        },
        onCategorySelected = { selectedCategoryId ->
            tablesViewModel.filterFoodsByCategory(selectedCategoryId)
        },
        onRefreshButtonClicked = { tablesViewModel.loadFoodAndCategoriesData() },
        onFoodCardClick = {
            tablesViewModel.addItemToCart(it)
        },
        onClearCart = {
            tablesViewModel.clearCart()
        }
    )
}

@Composable
fun TablesContent(
    modifier: Modifier = Modifier,
    tablesUiState: TablesScreenUiState,
    searchQuery: String,
    cartItems: List<CartItemUiModel>,
    gridCellsCount: Int,
    onQueryChanged: (String) -> Unit,
    onCategorySelected: (selectedCategoryId: Int) -> Unit,
    onRefreshButtonClicked: () -> Unit,
    onFoodCardClick: (foodItem: FoodItemUiModel) -> Unit,
    onClearCart: () -> Unit,
) {
    val insets = WindowInsets.ime
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val categoriesList = remember(tablesUiState) {
        when (tablesUiState) {
            is TablesScreenUiState.FetchedTableData -> tablesUiState.categoryList
            is TablesScreenUiState.FilteredFoodsByCategory -> tablesUiState.categoriesList
            is TablesScreenUiState.SearchedFoodsByName -> tablesUiState.categoriesList
            is TablesScreenUiState.LoadingFoodList -> tablesUiState.categoriesList
            else -> emptyList()
        }
    }

    val foodList = remember(tablesUiState) {
        when (tablesUiState) {
            is TablesScreenUiState.FetchedTableData -> tablesUiState.foodList
            is TablesScreenUiState.FilteredFoodsByCategory -> tablesUiState.filteredFoodList
            is TablesScreenUiState.SearchedFoodsByName -> tablesUiState.searchedFoodList
            else -> emptyList()
        }
    }

    var totalCount by remember { mutableIntStateOf(0) }
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(cartItems) {
        totalCount = cartItems.sumOf { it.quantity }
        totalPrice = cartItems.sumOf { (it.price * it.quantity).toDouble() }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            SearchBar(
                query = searchQuery,
                onQueryChanged = onQueryChanged
            )
            if (categoriesList.isNotEmpty()) {
                CategoriesTabs(
                    categoryList = categoriesList,
                    onCategorySelected = {
                        onCategorySelected(it)
                    },
                    selectedTabIndex = selectedTabIndex,
                    onSelectedTabIndex = {
                        selectedTabIndex = it
                    }
                )
            }

            when (tablesUiState) {
                is TablesScreenUiState.EmptyState -> {
                    EmptySection()
                }

                is TablesScreenUiState.LoadingScreen -> {
                    if (tablesUiState.isLoading) {
                        Spacer(modifier = Modifier.height(8.dp))
                        AnimateShimmerCategoriesBar()
                        Spacer(modifier = Modifier.height(8.dp))
                        AnimateShimmerFoodList(gridCellsCount)
                    }
                }

                is TablesScreenUiState.FetchedTableData -> {
                    FoodListSection(
                        foodList = foodList,
                        cartItems = cartItems,
                        insets = insets,
                        gridCellsCount = gridCellsCount,
                        onFoodCardClick = {
                            onFoodCardClick(it)
                        }
                    )
                }

                is TablesScreenUiState.LoadingFoodList -> {
                    if (tablesUiState.isLoading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimateShimmerFoodList(gridCellsCount)
                    }
                }

                is TablesScreenUiState.FilteredFoodsByCategory -> {
                    FoodListSection(
                        foodList = foodList,
                        insets = insets,
                        gridCellsCount = gridCellsCount,
                        onFoodCardClick = {
                            onFoodCardClick(it)
                        },
                        cartItems = cartItems
                    )
                }

                is TablesScreenUiState.SearchedFoodsByName -> {
                    FoodListSection(
                        foodList = foodList,
                        insets = insets,
                        gridCellsCount = gridCellsCount,
                        onFoodCardClick = {
                            onFoodCardClick(it)
                        },
                        cartItems = cartItems
                    )
                }

                is TablesScreenUiState.ApiError -> {
                    ErrorSection(
                        onRefreshButtonClicked = onRefreshButtonClicked,
                        customApiErrorExceptionUiModel = tablesUiState.customApiErrorExceptionUiModel
                    )
                }

                is TablesScreenUiState.DatabaseError -> {
                    ErrorSection(
                        onRefreshButtonClicked = onRefreshButtonClicked,
                        customDatabaseExceptionUiModel = tablesUiState.customDatabaseExceptionUiModel
                    )
                }

            }
        }

        ViewOrderButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            totalCount = totalCount.toString(),
            totalPrice = totalPrice.toTwoDecimalPlaces().toString(),
            onClick = {
                onClearCart()
            },
        )
    }

}

@PreviewLightDark
@DevicesPreviews
@Composable
fun PreviewTablesScreen() {
    FoodicsTaskTheme {
        TablesContent(
            tablesUiState = TablesScreenUiState.LoadingScreen(isLoading = true),
            searchQuery = "",
            gridCellsCount = 2,
            cartItems = emptyList(),
            onQueryChanged = {},
            onCategorySelected = { },
            onRefreshButtonClicked = {},
            onFoodCardClick = {},
            onClearCart = {}
        )
    }
}
