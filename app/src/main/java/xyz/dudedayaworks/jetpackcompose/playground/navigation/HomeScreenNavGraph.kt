package xyz.dudedayaworks.jetpackcompose.playground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(startDestination = Screen.NewsFeed.route, route = Screen.Home.route) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        @Suppress("DEPRECATION")
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST) { type = FeedPost.NavigationType },
            )
        ) {
            val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                ?: error("Feed post was not passed")
            commentsScreenContent(feedPost)
        }
    }
}