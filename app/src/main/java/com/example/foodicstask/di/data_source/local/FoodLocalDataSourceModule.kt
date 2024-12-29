package com.example.foodicstask.di.data_source.local

import com.example.foodicstask.data.data_sources.local.FoodLocalDataSource
import org.koin.dsl.module

val foodLocalDataSourceModule = module {
    single { FoodLocalDataSource(get()) }
}