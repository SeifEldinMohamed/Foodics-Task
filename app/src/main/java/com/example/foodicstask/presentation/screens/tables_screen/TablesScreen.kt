import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.foodicstask.presentation.screens.tables_screen.components.FoodItem
import com.example.foodicstask.presentation.screens.tables_screen.components.ViewOrderButton
import com.example.foodicstask.presentation.screens.tables_screen.components.shimmer_animation.AnimateShimmerCategoriesBar
import com.example.foodicstask.presentation.utils.getGridCellsCount
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TablesScreen(
    windowSize: WindowSizeClass
) {
    val tablesViewModel = koinViewModel<TablesViewModel>()
    val tablesUiState = tablesViewModel.tablesScreenUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        tablesViewModel.requestFoodList()
    }
    TablesContent(
        tablesUiState = tablesUiState.value,
        searchQuery = "",
        gridCellsCount = windowSize.getGridCellsCount(),
        onQueryChanged = {},
        onCategorySelected = {},
        onRefreshButtonClicked = { tablesViewModel.requestFoodList() }
    )
}

@Composable
fun TablesContent(
    tablesUiState: TablesScreenUiState,
    searchQuery: String,
    gridCellsCount: Int,
    onQueryChanged: (String) -> Unit,
    onCategorySelected: (selectedIndex:Int) -> Unit,
    onRefreshButtonClicked: () -> Unit
) {
    val insets = WindowInsets.ime

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = onQueryChanged
        )

        when (tablesUiState) {
            is TablesScreenUiState.EmptyState -> {
                EmptySection()
            }

            is TablesScreenUiState.Loading -> {
                if (tablesUiState.isLoading) {
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimateShimmerCategoriesBar()
                    Spacer(modifier = Modifier.height(8.dp))
                    AnimateShimmerFoodList(gridCellsCount)
                }
            }

            is TablesScreenUiState.FetchedTableData -> {
                CategoriesTabs(
                    categoryList = tablesUiState.categoryList,
                    onCategorySelected = onCategorySelected
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(gridCellsCount),
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    itemsIndexed(tablesUiState.foodList) { index, foodItem ->
                        FoodItem(
                            index = index,
                            foodItemUiModel = foodItem,
                            countFoodItemInCart = 0,
                            onFoodCardClick = {}
                        )
                    }
                    item {
                        Spacer(
                            modifier = Modifier.height(
                                (insets.asPaddingValues()
                                    .calculateBottomPadding()) / 2
                            )
                        )
                    }
                }

                ViewOrderButton(
                    onClick = {},
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
}

@PreviewLightDark
@DevicesPreviews
@Composable
fun PreviewTablesScreen() {
    FoodicsTaskTheme {
        TablesContent(
            tablesUiState = TablesScreenUiState.Loading(isLoading = true),
            searchQuery = "",
            gridCellsCount = 2,
            onQueryChanged = {},
            onCategorySelected = {},
            onRefreshButtonClicked = {}
        )
    }
}
