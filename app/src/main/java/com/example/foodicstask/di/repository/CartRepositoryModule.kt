package com.example.foodicstask.di.repository


import com.example.foodicstask.data.repository.CartRepositoryImpl
import com.example.foodicstask.domain.repository.CartRepository
import com.example.foodicstask.domain.usecase.cart.AddItemToCartUseCase
import com.example.foodicstask.domain.usecase.cart.ClearCartUseCase
import com.example.foodicstask.domain.usecase.cart.GetAllCartItemsUseCase
import org.koin.dsl.module

val cartRepositoryModule = module {
    single<CartRepository> { CartRepositoryImpl(get()) }
    single<AddItemToCartUseCase> { AddItemToCartUseCase(get()) }
    single<ClearCartUseCase> { ClearCartUseCase(get()) }
    single<GetAllCartItemsUseCase> { GetAllCartItemsUseCase(get()) }
}