package com.example.foodicstask.presentation.fake_data

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

val fakeFoodItemUiModelList = listOf(
    FoodItemUiModel(
        id = 1,
        name = "Margherita Pizza",
        description = "Classic pizza with fresh mozzarella, basil, and tomato sauce.",
        image = "https://example.com/images/margherita_pizza.jpg",
        price = 8.99f,
        categoryId = 1
    ),
    FoodItemUiModel(
        id = 2,
        name = "Chicken Caesar Salad",
        description = "Crispy romaine lettuce, grilled chicken, croutons, and Caesar dressing.",
        image = "https://example.com/images/chicken_caesar_salad.jpg",
        price = 6.49f,
        categoryId = 2
    ),
    FoodItemUiModel(
        id = 3,
        name = "Cheeseburger",
        description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles on a toasted bun.",
        image = "https://example.com/images/cheeseburger.jpg",
        price = 7.99f,
        categoryId = 3
    )
)

val fakeCategoryListUiModel = listOf(
    CategoryUiModel(id = 1, name = "Breakfast"),
    CategoryUiModel(id = 2, name = "Lunch"),
    CategoryUiModel(id = 3, name = "Dinner"),
    CategoryUiModel(id = 4, name = "Sweets"),
    CategoryUiModel(id = 5, name = "Snacks"),
    CategoryUiModel(id = 6, name = "Appetizers"),
    CategoryUiModel(id = 7, name = "Label"),
)