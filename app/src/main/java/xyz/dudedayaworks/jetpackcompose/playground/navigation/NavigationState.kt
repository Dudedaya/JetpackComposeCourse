package xyz.dudedayaworks.jetpackcompose.playground.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost

class NavigationState(
    val navHostController: NavHostController,
) {

    fun navigateOnMainTo(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(Screen.Main.route) {
                saveState = true
            }
            restoreState = true
        }
    }

    fun navigateToComments(feedPost: FeedPost) {
        navHostController.navigate(Screen.Comments.getRouteWithArgs(feedPost))
    }

    fun navigateToMain() {
        navHostController.navigate(Screen.Main.route) {
            popUpTo(navHostController.graph.id) {
                inclusive = true
            }
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController(),
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}