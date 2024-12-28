package com.example.foodicstask.presentation.screens.tables_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeCategoryListUiModel
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@Composable
fun CategoriesTabs(
    selectedTabIndex: Int,
    categoryList: List<CategoryUiModel>,
    onCategorySelected: (selectedIndex: Int) -> Unit,
    onSelectedTabIndex: (selectedIndex: Int) -> Unit,
) {

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(
                    tabPositions[selectedTabIndex]
                ),
                height = 3.dp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    ) {
        categoryList.forEachIndexed { index, categoryUiModel ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    if (selectedTabIndex != index) {
                        onCategorySelected(index)
                    }
                    onSelectedTabIndex(index)
                },
                text = {
                    Text(
                        text = categoryUiModel.name,
                        style = if (selectedTabIndex == index) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategoriesTabs() {
    FoodicsTaskTheme {
        CategoriesTabs(
            categoryList = fakeCategoryListUiModel,
            onCategorySelected = {},
            selectedTabIndex = 0,
            onSelectedTabIndex = {}
        )
    }
}