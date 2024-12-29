package com.example.foodicstask.di.feature

import com.example.foodicstask.presentation.screens.tables_screen.TablesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val tablesModule = module {
    viewModel { TablesViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
}