package com.example.foodicstask.domain.fake_data

import com.example.foodicstask.domain.model.CartItemDomainModel
import com.example.foodicstask.domain.model.CategoryDomainModel
import com.example.foodicstask.domain.model.FoodItemDomainModel

val fakeFoodItemsDomainModelList = listOf(
    FoodItemDomainModel(
        id = 1,
        name = "Margherita Pizza",
        description = "Classic pizza with fresh mozzarella, basil, and tomato sauce.",
        image = "https://example.com/images/margherita_pizza.jpg",
        price = 8.99f,
        categoryId = 1
    ),
    FoodItemDomainModel(
        id = 2,
        name = "Chicken Caesar Salad",
        description = "Crispy romaine lettuce, grilled chicken, croutons, and Caesar dressing.",
        image = "https://example.com/images/chicken_caesar_salad.jpg",
        price = 6.49f,
        categoryId = 2
    ),
    FoodItemDomainModel(
        id = 3,
        name = "Cheeseburger",
        description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles on a toasted bun.",
        image = "https://example.com/images/cheeseburger.jpg",
        price = 7.99f,
        categoryId = 3
    )
)

val fakeSearchedFoodItemsList = listOf(
    FoodItemDomainModel(
        id = 2,
        name = "Burger",
        description = "Crispy romaine lettuce, grilled chicken, croutons, and Caesar dressing.",
        image = "https://example.com/images/chicken_caesar_salad.jpg",
        price = 6.49f,
        categoryId = 2
    ),
    FoodItemDomainModel(
        id = 3,
        name = "Cheeseburger",
        description = "Juicy beef patty with cheddar cheese, lettuce, tomato, and pickles on a toasted bun.",
        image = "https://example.com/images/cheeseburger.jpg",
        price = 7.99f,
        categoryId = 2
    )
)

val fakeCategoriesDomainModelList = listOf(
    CategoryDomainModel(
        id = 1,
        name = "Pizza"
    ),
    CategoryDomainModel(
        id = 2,
        name = "Salads"
    ),
    CategoryDomainModel(
        id = 3,
        name = "Burgers"
    ),
    CategoryDomainModel(
        id = 4,
        name = "Desserts"
    ),
    CategoryDomainModel(
        id = 5,
        name = "Beverages"
    )
)

val fakeCartItemDomainModelList = listOf(
    CartItemDomainModel(
        id = 1,
        name = "Apple",
        price = 0.99f,
        quantity = 5
    ),
    CartItemDomainModel(
        id = 2,
        name = "Banana",
        price = 0.59f,
        quantity = 8
    ),
    CartItemDomainModel(
        id = 3,
        name = "Orange Juice",
        price = 3.49f,
        quantity = 2
    ),
    CartItemDomainModel(
        id = 4,
        name = "Bread",
        price = 2.99f,
        quantity = 1
    )
)

val fakeCategory1 = CategoryDomainModel(id = 1, name = "Pizza")
val fakeCategory2 = CategoryDomainModel(id = 2, name = "Pasta")
val fakeCategory3 = CategoryDomainModel(id = 3, name = "Salad")
val fakeCategory4 = CategoryDomainModel(id = 4, name = "Dessert")

val fakeCategoryDomainModelList = listOf(
    fakeCategory1,
    fakeCategory2,
    fakeCategory3,
    fakeCategory4,
)

