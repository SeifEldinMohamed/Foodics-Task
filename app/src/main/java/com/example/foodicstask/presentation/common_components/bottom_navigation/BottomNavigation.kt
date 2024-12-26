package com.example.foodicstask.presentation.common_components.bottom_navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.navigation.Screens
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme


@Composable
fun BottomNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>, currentScreen: Screens?, onMenuItemClick: (Screens) -> Unit
) {
    NavigationBar(modifier = Modifier.height(64.dp),
                  containerColor = MaterialTheme.colorScheme.surface,
                  content = {
                      navigationMenuItems.forEach { menuItem ->
                          val screenForMenuItem = menuItem.screen
                          NavigationBarItem(
                              selected = currentScreen == screenForMenuItem,
                              onClick = {
                                  onMenuItemClick(screenForMenuItem)
                              },
                              label = {
                                  Text(
                                      text = stringResource(id = menuItem.title),
                                  )
                              },
                              enabled = true,
                              icon = {
                                  Icon(
                                      painter = painterResource(id = menuItem.icon),
                                      contentDescription = stringResource(id = menuItem.title)
                                  )
                              },
                              alwaysShowLabel = true,
                              colors = NavigationBarItemDefaults.colors(
                                  selectedIconColor = MaterialTheme.colorScheme.onSurface,
                                  unselectedIconColor = Color.Gray,
                                  selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                  unselectedTextColor = Color.Gray,
                                  indicatorColor = Color.Transparent
                              )
                          )
                      }
                  })
}

@Preview
@Composable
fun PreviewBottomNavigationBar() {
    FoodicsTaskTheme {
        BottomNavigationBar(navigationMenuItems = enumValues<NavigationMenuItem>(),
                            currentScreen = Screens.TablesScreen,
                            onMenuItemClick = {})
    }
}