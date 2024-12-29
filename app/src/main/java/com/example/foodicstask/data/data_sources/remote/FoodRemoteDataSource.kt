package com.example.foodicstask.data.data_sources.remote

import com.example.foodicstask.data.Constants.Companion.CATEGORIES_ENDPOINT
import com.example.foodicstask.data.Constants.Companion.PRODUCTS_ENDPOINT
import com.example.foodicstask.data.data_sources.remote.ktor.api.FoodApi
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.CategoryDataModel
import com.example.foodicstask.data.data_sources.remote.ktor.model.tables.FoodItemDataModel
import com.example.foodicstask.data.mapper.exceptions.toCustomApiExceptionDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class FoodRemoteDataSource(
    private val client: HttpClient,
) : FoodApi {
    override suspend fun fetchFoodList(): List<FoodItemDataModel> {
        return try {
            client.get {
                url(PRODUCTS_ENDPOINT)
            }.body<List<FoodItemDataModel>>()
        } catch (e: Exception) {
            throw e.toCustomApiExceptionDomainModel()
        }
    }

    override suspend fun fetchCategoryList(): List<CategoryDataModel> {
        return try {
            client.get {
                url(CATEGORIES_ENDPOINT)
            }.body<List<CategoryDataModel>>()
        } catch (e: Exception) {
            throw e.toCustomApiExceptionDomainModel()
        }
    }
}

