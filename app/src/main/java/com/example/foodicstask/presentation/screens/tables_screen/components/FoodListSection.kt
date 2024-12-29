package com.example.foodicstask.presentation.screens.tables_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.screens.tables_screen.model.CartItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeFoodItemListUiModel
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@Composable
fun ColumnScope.FoodListSection(
    foodList: List<FoodItemUiModel>,
    insets: WindowInsets,
    gridCellsCount: Int,
    onFoodCardClick: (foodItem: FoodItemUiModel) -> Unit,
    cartItems: List<CartItemUiModel>,
) {

    val keyboardHeight =
        with(LocalDensity.current) { insets.getBottom(LocalDensity.current).toDp() } / 2
    val cartItemsMap = cartItems.associateBy { it.id }
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCellsCount),
        contentPadding = PaddingValues(
            start = 4.dp,
            top = 4.dp,
            end = 4.dp,
            bottom = 4.dp + keyboardHeight
        ),
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        items(foodList) { foodItem ->
            FoodItem(
                foodItemUiModel = foodItem,
                quantityInCart = cartItemsMap[foodItem.id]?.quantity ?: 0,
                onFoodCardClick = {
                    onFoodCardClick(it)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewFoodListSection() {
    FoodicsTaskTheme {
        val insets = WindowInsets.ime
        Column {
            FoodListSection(
                foodList = fakeFoodItemListUiModel,
                insets = insets,
                gridCellsCount = 2,
                onFoodCardClick = {},
                cartItems = emptyList(),
            )
        }
    }
}
