package xyz.dudedayaworks.jetpackcompose.playground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    loginScreenContent: @Composable () -> Unit,
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login.route,
    ) {
        composable(route = Screen.Login.route) {
            loginScreenContent()
        }
        mainScreenNavGraph(
            newsFeedScreenContent = newsFeedScreenContent,
            commentsScreenContent = commentsScreenContent,
            favoriteScreenContent = favoriteScreenContent,
            profileScreenContent = profileScreenContent,
        )
    }
}
