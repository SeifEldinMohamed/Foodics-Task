package com.example.foodicstask.presentation.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class StandardDispatcherProvider: DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}