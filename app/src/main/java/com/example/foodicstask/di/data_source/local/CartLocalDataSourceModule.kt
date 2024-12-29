package com.example.foodicstask.di.data_source.local

import com.example.foodicstask.data.data_sources.local.CartLocalDataSource
import org.koin.dsl.module

val cartLocalDataSourceModule = module {
    single { CartLocalDataSource(get()) }
}