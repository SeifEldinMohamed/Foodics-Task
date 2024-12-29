package com.example.foodicstask.di


import com.example.foodicstask.di.data_source.local.cartLocalDataSourceModule
import com.example.foodicstask.di.data_source.local.foodLocalDataSourceModule
import com.example.foodicstask.di.data_source.remote.foodRemoteDataSourceModule
import com.example.foodicstask.di.feature.tablesModule
import com.example.foodicstask.di.repository.cartRepositoryModule
import com.example.foodicstask.di.repository.foodRepositoryModule
import org.koin.dsl.module

val appModule = module {
    includes(
        databaseModule,
        foodRemoteDataSourceModule,
        foodLocalDataSourceModule,
        foodRepositoryModule,
        dispatchersModule,
        tablesModule,
        cartRepositoryModule,
        cartLocalDataSourceModule
    )
}