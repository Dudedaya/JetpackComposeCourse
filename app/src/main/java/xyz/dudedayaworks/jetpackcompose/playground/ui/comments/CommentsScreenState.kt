package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostComment

sealed class CommentsScreenState {
    object Initial : CommentsScreenState()
    object Loading : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) : CommentsScreenState()
}