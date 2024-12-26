package com.example.foodicstask.presentation.navigation

import TablesScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodicstask.presentation.screens.menu_screen.MenuScreen
import com.example.foodicstask.presentation.screens.orders_screen.OrdersScreen
import com.example.foodicstask.presentation.screens.settings_screen.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screens.TablesScreen
    ) {
        composable<Screens.TablesScreen> {
            TablesScreen(
                windowSize = windowSizeClass
            )
        }

        composable<Screens.OrdersScreen> {
            OrdersScreen()
        }

        composable<Screens.MenuScreen> {
            MenuScreen()
        }

        composable<Screens.SettingsScreen>{
            SettingsScreen()
        }
    }
}
