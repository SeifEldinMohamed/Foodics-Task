package com.example.foodicstask.di.repository


import com.example.foodicstask.data.repository.FoodRepositoryImpl
import com.example.foodicstask.domain.repository.FoodRepository
import com.example.foodicstask.domain.usecase.FetchCategoriesListUseCase
import com.example.foodicstask.domain.usecase.FetchFoodListUseCase
import com.example.foodicstask.domain.usecase.FilteredFoodListByCategoryUseCase
import com.example.foodicstask.domain.usecase.SearchFoodListByNameUseCase
import org.koin.dsl.module

val foodRepositoryModule = module {
    single<FoodRepository> { FoodRepositoryImpl(get(), get()) }
    single<FetchFoodListUseCase> { FetchFoodListUseCase(get()) }
    single<FilteredFoodListByCategoryUseCase> { FilteredFoodListByCategoryUseCase(get()) }
    single<FetchCategoriesListUseCase> { FetchCategoriesListUseCase(get()) }
    single<SearchFoodListByNameUseCase> { SearchFoodListByNameUseCase(get()) }
}