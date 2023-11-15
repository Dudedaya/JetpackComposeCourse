package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import xyz.dudedayaworks.jetpackcompose.playground.domain.models.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.models.PostComment

sealed class CommentsScreenState {
    object Loading : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) : CommentsScreenState()
}