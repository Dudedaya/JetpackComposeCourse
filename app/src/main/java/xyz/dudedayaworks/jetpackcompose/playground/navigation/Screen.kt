package xyz.dudedayaworks.jetpackcompose.playground.navigation

import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost

sealed class Screen(
    val route: String,
) {

    object Home : Screen(ROUTE_HOME)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(feedPost: FeedPost): String {
            return "$ROUTE_FOR_ARGS/${feedPost.id}/${feedPost.title}"
        }
    }

    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    companion object {
        const val KEY_FEED_POST_ID = "feed_post_id"
        const val KEY_FEED_POST_TITLE = "feed_post_title"
        private const val ROUTE_HOME = "home"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_FEED_POST_TITLE}"
        private const val ROUTE_FAVORITE = "favorite"
        private const val ROUTE_PROFILE = "profile"
    }
}
