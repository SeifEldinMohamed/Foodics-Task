package com.example.foodicstask.di.data_source.remote


import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import com.example.foodicstask.data.data_sources.remote.ktor.KtorHttpClient
import org.koin.dsl.module

val foodRemoteDataSourceModule = module {
    single<FoodRemoteDataSource> { FoodRemoteDataSource(KtorHttpClient().get()) }
}