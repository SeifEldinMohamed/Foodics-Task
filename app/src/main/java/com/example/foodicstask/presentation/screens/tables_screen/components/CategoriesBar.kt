package com.example.foodicstask.presentation.screens.tables_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeCategoryListUiModel
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@Composable
fun CategoriesTabs(
    categoryList: List<CategoryUiModel>,
    onCategorySelected: (selectedIndex:Int) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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
                    selectedTabIndex = index
                    onCategorySelected(selectedTabIndex)
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
            onCategorySelected = {}
        )
    }
}