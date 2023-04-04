package com.mungai.intothepotter_verse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mungai.intothepotter_verse.presentation.collection.screen.CollectionScreen
import com.mungai.intothepotter_verse.presentation.detail.screen.CharacterDetailsScreen
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
        composable(
            BottomNavItem.Search.route, arguments = listOf(
                navArgument(name = "query") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            SearchScreen(navController = navController, bottomPadding = bottomPadding)
        }
        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavController) {
    navigation(
        route = Graphs.SECONDARY,
        startDestination = "character_details_screen"
    ) {
        composable(
            "character_details_screen" + "?characterId={characterId}",
            arguments = listOf(
                navArgument(name = "characterId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) {
            CharacterDetailsScreen(navController = navController)
        }
        composable("collection_screen?collection={collection}&house={house}", arguments = listOf(
            navArgument(name = "collection") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            },
            navArgument(name = "house") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )) {
            CollectionScreen(navController = navController)
        }
    }
}

