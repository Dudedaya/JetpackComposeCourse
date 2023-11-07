package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import xyz.dudedayaworks.jetpackcompose.playground.R
import xyz.dudedayaworks.jetpackcompose.playground.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector,
) {
    object Home : NavigationItem(
        screen = Screen.NewsFeed,
        titleResId = R.string.navigation_title_home,
        icon = Icons.Outlined.Home,
    )

    object Favorites : NavigationItem(
        screen = Screen.Favorite,
        titleResId = R.string.navigation_title_favorites,
        icon = Icons.Outlined.Favorite,
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_title_profile,
        icon = Icons.Outlined.Person,
    )
}