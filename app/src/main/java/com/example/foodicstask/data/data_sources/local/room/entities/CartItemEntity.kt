package com.example.foodicstask.data.data_sources.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Float,
    val quantity: Int,
)