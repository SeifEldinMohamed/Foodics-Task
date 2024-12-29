package com.example.foodicstask.domain.model

data class CartItemDomainModel(
    val id: Int,
    val name: String,
    val price: Float,
    val quantity: Int,
)