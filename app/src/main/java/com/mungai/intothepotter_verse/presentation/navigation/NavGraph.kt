package com.mungai.intothepotter_verse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mungai.intothepotter_verse.presentation.detail.screen.DetailsScreen
import com.mungai.intothepotter_verse.presentation.home.screen.HomeScreen
import com.mungai.intothepotter_verse.presentation.navigation.bottom_navigation.BottomNavItem
import com.mungai.intothepotter_verse.presentation.search.screen.SearchScreen

@Composable
fun NavGraph(navController: NavHostController, bottomPadding: Dp) {
    NavHost(
        navController = navController,
        route = Graphs.MAIN,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController = navController, bottomPadding = bottomPadding)
        }
        composable(BottomNavItem.Search.route) {
            SearchScreen(navController = navController, bottomPadding = bottomPadding)
        }
        detailsNavGraph()
    }
}


fun NavGraphBuilder.detailsNavGraph() {
    navigation(
        route = Graphs.SECONDARY,
        startDestination = "details_screen"
    ) {
        composable("details_screen") {
            DetailsScreen()
        }
    }
}

