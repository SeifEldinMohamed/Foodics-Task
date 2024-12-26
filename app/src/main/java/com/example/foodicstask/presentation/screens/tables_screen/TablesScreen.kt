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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.common_components.SearchBar
import com.example.foodicstask.presentation.preview.DevicesPreviews
import com.example.foodicstask.presentation.screens.tables_screen.components.CategoriesTabs
import com.example.foodicstask.presentation.screens.tables_screen.components.FoodItem
import com.example.foodicstask.presentation.screens.tables_screen.components.ViewOrderButton
import com.example.foodicstask.presentation.screens.utils.getGridCellsCount
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@Composable
fun TablesScreen(
    windowSize: WindowSizeClass
) {

    TablesContent(searchQuery = "",
                  gridCellsCount = windowSize.getGridCellsCount(),
                  onQueryChanged = {})
}

@Composable
fun TablesContent(
    searchQuery: String,
    gridCellsCount: Int,
    onQueryChanged: (String) -> Unit,
) {
    val insets = WindowInsets.ime

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChanged = onQueryChanged
        )
        CategoriesTabs()

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridCellsCount),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            items(20) { index ->
                FoodItem(index,
                         onFoodCardClick = {})
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
}

@DevicesPreviews
@Composable
fun PreviewTablesScreen() {
    FoodicsTaskTheme {
        TablesContent(searchQuery = "",
                      gridCellsCount = 2,
                      onQueryChanged = {})
    }
}
