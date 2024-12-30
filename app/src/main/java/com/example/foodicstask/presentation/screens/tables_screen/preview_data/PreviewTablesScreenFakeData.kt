package com.example.foodicstask.presentation.screens.tables_screen.preview_data

import com.example.foodicstask.presentation.screens.tables_screen.model.CategoryUiModel
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel

val fakeFoodItemUiModel = FoodItemUiModel(
    id = 1,
    name = "pancakes",
    description = "Fluffy pancakes with maple syrup",
    image = "https://www.foodiesfeed.com/wp-content/uploads/2023/06/pouring-honey-on-pancakes.jpg",
    price = 33.7f,
    categoryId = 1
)

val fakeFoodItemUiModel2 = FoodItemUiModel(
    id = 2,
    name = "pancakes",
    description = "Fluffy pancakes with maple syrup",
    image = "https://www.foodiesfeed.com/wp-content/uploads/2023/06/pouring-honey-on-pancakes.jpg",
    price = 33.7f,
    categoryId = 1
)

val fakeFoodItemListUiModel = listOf(
    fakeFoodItemUiModel,
    fakeFoodItemUiModel2
)

val fakeCategoryListUiModel = listOf(
    CategoryUiModel(id = 0, name = "All"),
    CategoryUiModel(id = 1, name = "Breakfast"),
    CategoryUiModel(id = 2, name = "Lunch"),
    CategoryUiModel(id = 3, name = "Dinner"),
    CategoryUiModel(id = 4, name = "Sweets"),
    CategoryUiModel(id = 5, name = "Snacks"),
    CategoryUiModel(id = 6, name = "Appetizers"),
    CategoryUiModel(id = 7, name = "Label"),
)