package xyz.dudedayaworks.jetpackcompose.playground.ui

import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostComment

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Posts(val posts: List<FeedPost>) : HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}