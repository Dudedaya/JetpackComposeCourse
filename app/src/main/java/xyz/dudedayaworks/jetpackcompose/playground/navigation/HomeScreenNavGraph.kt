package xyz.dudedayaworks.jetpackcompose.playground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(startDestination = Screen.NewsFeed.route, route = Screen.Home.route) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(route = Screen.Comments.route) {
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: -1
            commentsScreenContent(FeedPost.preview(feedPostId))
        }
    }
}