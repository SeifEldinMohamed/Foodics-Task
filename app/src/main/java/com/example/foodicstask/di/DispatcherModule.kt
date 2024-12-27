package com.example.foodicstask.di

import com.example.foodicstask.presentation.utils.DispatcherProvider
import com.example.foodicstask.presentation.utils.StandardDispatcherProvider
import org.koin.dsl.module

val dispatchersModule = module {
    single<DispatcherProvider> { StandardDispatcherProvider() }
}