package com.example.foodicstask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodicstask.presentation.common_components.bottom_navigation.BottomNavigationBar
import com.example.foodicstask.presentation.common_components.bottom_navigation.NavigationMenuItem
import com.example.foodicstask.presentation.navigation.AppNavHost
import com.example.foodicstask.presentation.navigation.Screens
import com.example.foodicstask.presentation.screens.tables_screen.components.TopSection
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            FoodicsTaskTheme {
                val navController = rememberNavController()

                val currentNavigationBarDestination =
                    navController.currentBackStackEntryAsState().value.currentDestinationFromNavigationBar(
                        enumValues<NavigationMenuItem>().map { it.screen })

                Scaffold(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = {
                        TopSection()
                    },
                    content = { innerPadding ->
                        AppNavHost(
                            navController = navController,
                            innerPadding = innerPadding,
                            windowSizeClass = windowSize
                        )
                    },
                    bottomBar = {
                        val navigationMenuItems = enumValues<NavigationMenuItem>()
                        BottomNavigationBar(
                            navigationMenuItems = navigationMenuItems,
                            currentScreen = currentNavigationBarDestination,
                            onMenuItemClick = {
                                navController.singleTopNavigate(it)
                            })
                    })
            }
        }
    }
}

private fun NavBackStackEntry?.currentDestinationFromNavigationBar(
    navigationBarItems: List<Screens>,
): Screens? {
    var currentDestination: Screens? = null

    navigationBarItems.forEach { item ->
        if (this?.destination?.hierarchy?.any {
                it.hasRoute(item::class)
            } == true) {
            currentDestination = item
        }
    }

    return currentDestination
}

private fun NavHostController.singleTopNavigate(screen: Screens) {
    navigate(screen) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop =
            true // Avoid multiple copies of the same destination when reselecting the same item
        restoreState = true   // Restore state when reselecting a previously selected item
    }
}

