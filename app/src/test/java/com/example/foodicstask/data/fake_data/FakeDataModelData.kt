package com.example.foodicstask.data.fake_data

import com.example.foodicstask.data.data_sources.local.room.entities.CartItemEntity
import com.example.foodicstask.data.data_sources.local.room.entities.CategoryEntity
import com.example.foodicstask.data.data_sources.local.room.entities.FoodItemEntity
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.CategoryDataModel
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.FoodItemDataModel


val fakeCategory1 = CategoryDataModel(id = 1, name = "Pizza")
val fakeCategory2 = CategoryDataModel(id = 2, name = "Pasta")
val fakeCategory3 = CategoryDataModel(id = 3, name = "Salad")

val fakeCategoryListDataModel = listOf(
    fakeCategory1,
    fakeCategory2,
    fakeCategory3,
)

val fakeFoodItemListDataModel = listOf(
    FoodItemDataModel(
        id = 1,
        name = "Margherita Pizza",
        description = "Classic pizza with fresh mozzarella, basil, and tomato sauce.",
        image = "https://example.com/images/margherita_pizza.jpg",
        price = 8.99f,
        categoryDataModel = fakeCategory1
    ),
    FoodItemDataModel(
        id = 2,
        name = "Chicken Caesar Salad",
        description = "Crispy romaine lettuce, grilled chicken, croutons, and Caesar dressing.",
        image = "https://example.com/images/chicken_caesar_salad.jpg",
        price = 6.49f,
        categoryDataModel = fakeCategory2
    ),
    FoodItemDataModel(
        id = 3,
        name = "Cheeseburger",
        description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles on a toasted bun.",
        image = "https://example.com/images/cheeseburger.jpg",
        price = 7.99f,
        categoryDataModel = fakeCategory3
    )
)

// Entities

val fakeFoodItemEntityList = listOf(
    FoodItemEntity(
        id = 1,
        name = "Margherita Pizza",
        description = "Classic pizza with fresh mozzarella, basil, and tomato sauce.",
        image = "https://example.com/images/margherita_pizza.jpg",
        price = 8.99f,
        categoryId = 1
    ),
    FoodItemEntity(
        id = 2,
        name = "Chicken Caesar Salad",
        description = "Crispy romaine lettuce, grilled chicken, croutons, and Caesar dressing.",
        image = "https://example.com/images/chicken_caesar_salad.jpg",
        price = 6.49f,
        categoryId = 2
    ),
    FoodItemEntity(
        id = 3,
        name = "Cheeseburger",
        description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles on a toasted bun.",
        image = "https://example.com/images/cheeseburger.jpg",
        price = 7.99f,
        categoryId = 3
    )
)

val fakeCategoryEntityList = listOf(
    CategoryEntity(
        id = 1,
        name = "Pizza"
    ),
    CategoryEntity(
        id = 2,
        name = "Pasta"
    ),
    CategoryEntity(
        id = 3,
        name = "Salad"
    )
)

val fakeCartItemEntityList = listOf(
    CartItemEntity(
        id = 1,
        name = "Apple",
        price = 0.99f,
        quantity = 5
    ),
    CartItemEntity(
        id = 2,
        name = "Banana",
        price = 0.59f,
        quantity = 8
    ),
    CartItemEntity(
        id = 3,
        name = "Orange Juice",
        price = 3.49f,
        quantity = 2
    ),
    CartItemEntity(
        id = 4,
        name = "Bread",
        price = 2.99f,
        quantity = 1
    )
)

