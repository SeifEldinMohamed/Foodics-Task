package com.example.foodicstask.di

import androidx.room.Room
import com.example.foodicstask.data.Constants.Companion.DATABASE_NAME
import com.example.foodicstask.data.data_sources.local.room.CartDao
import com.example.foodicstask.data.data_sources.local.room.FoodDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<FoodDatabase> {
        Room.databaseBuilder(
            androidContext(),
            FoodDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<FoodDatabase>().foodDao() }
    single { get<FoodDatabase>().cartDao() }
}