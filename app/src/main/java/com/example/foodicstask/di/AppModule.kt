package com.example.foodicstask.di


import org.koin.dsl.module

val appModule = module {
    includes(
        foodRemoteDataSourceModule,
        foodRepositoryModule,
        dispatchersModule,
        tablesModule,
    )
}