package com.example.foodicstask.di


import org.koin.dsl.module

val appModule = module {
    includes(
        databaseModule,
        foodRemoteDataSourceModule,
        foodLocalDataSourceModule,
        foodRepositoryModule,
        dispatchersModule,
        tablesModule,
    )
}