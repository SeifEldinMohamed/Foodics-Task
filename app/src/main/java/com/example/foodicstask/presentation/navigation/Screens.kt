package com.example.foodicstask.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object TablesScreen : Screens()
    @Serializable

    data object OrdersScreen : Screens()
    @Serializable

    data object MenuScreen : Screens()
    @Serializable

    data object SettingsScreen : Screens()

}