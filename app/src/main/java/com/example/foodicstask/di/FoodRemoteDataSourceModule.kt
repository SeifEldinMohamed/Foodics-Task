package com.example.foodicstask.di


import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import com.example.foodicstask.data.data_sources.remote.ktor.KtorHttpClient
import org.koin.dsl.module

val foodRemoteDataSourceModule = module {
    single { FoodRemoteDataSource(KtorHttpClient().get()) }
}