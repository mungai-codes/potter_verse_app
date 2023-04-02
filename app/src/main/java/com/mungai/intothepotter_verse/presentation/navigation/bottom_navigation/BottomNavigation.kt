package com.mungai.intothepotter_verse.presentation.navigation.bottom_navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController) {

    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    // Only show the bottom bar when on the main graph
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Color(0xFFD86767),
            contentColor = Color.Black
        ) {
            screens.forEach { screen ->
                val isSelected = screen.route == navBackStackEntry.value?.destination?.route
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(imageVector = screen.icon, contentDescription = screen.title)
                            if (isSelected) {
                                Text(
                                    text = screen.title,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    },
                    selectedContentColor = Color(0xFFFFD700),
                    unselectedContentColor = Color(0xFF000000),
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }
    }

}