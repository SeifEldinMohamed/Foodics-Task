package com.example.foodicstask.di


import com.example.foodicstask.data.repository.FoodRepositoryImpl
import com.example.foodicstask.domain.repository.FoodRepository
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import org.koin.dsl.module

val foodRepositoryModule = module {
    single<FoodRepository> { FoodRepositoryImpl(get(), get()) }
    single<FetchFoodListUseCase> { FetchFoodListUseCase(get()) }
    single<FetchCategoriesListUseCase> { FetchCategoriesListUseCase(get()) }
}