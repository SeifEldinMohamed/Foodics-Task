package com.example.foodicstask.data.data_sources.remote.ktor.model.food_list

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDataModel(
    val id: Int,
    val name: String
)