package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.PostComment

sealed class CommentsScreenState {
    object Loading : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) : CommentsScreenState()
}