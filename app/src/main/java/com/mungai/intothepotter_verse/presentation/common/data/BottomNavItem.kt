package com.mungai.intothepotter_verse.presentation.common.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object Home : BottomNavItem(title = "Home", icon = Icons.Rounded.Home, route = "home_screen")
    object Search :
        BottomNavItem(
            title = "Search",
            icon = Icons.Rounded.Search,
            route = "search_screen?query={query}"
        )
}
