package xyz.dudedayaworks.jetpackcompose.playground.ui.newsfeed

import xyz.dudedayaworks.jetpackcompose.playground.domain.models.FeedPost

sealed class NewsFeedScreenState {
    object Loading : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}