package xyz.dudedayaworks.jetpackcompose.playground.navigation

import android.net.Uri
import com.google.gson.Gson
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost

sealed class Screen(
    val route: String,
) {

    object Login : Screen(ROUTE_LOGIN)
    object Main : Screen(ROUTE_MAIN)
    object Home : Screen(ROUTE_HOME)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${Gson().toJson(feedPost).encode()}"
        }
    }

    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    companion object {
        const val KEY_FEED_POST = "feed_post"
        private const val ROUTE_LOGIN = "login"
        private const val ROUTE_MAIN = "main"
        private const val ROUTE_HOME = "home"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        private const val ROUTE_FAVORITE = "favorite"
        private const val ROUTE_PROFILE = "profile"
    }
}

private fun String.encode(): String {
    return Uri.encode(this)
}