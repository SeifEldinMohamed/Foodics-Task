package com.example.foodicstask.presentation.common_components.bottom_navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.foodicstask.R
import com.example.foodicstask.presentation.navigation.Screens

enum class NavigationMenuItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen: Screens
) {
    TABLES(R.string.screen_title_tables, R.drawable.ic_tables, Screens.TablesScreen),
    ORDERS(R.string.screen_title_orders, R.drawable.ic_orders, Screens.OrdersScreen),
    MENU(R.string.screen_title_menu, R.drawable.ic_menu, Screens.MenuScreen),
    SETTINGS(R.string.screen_title_settings, R.drawable.ic_settings, Screens.SettingsScreen)
}
