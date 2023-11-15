package xyz.dudedayaworks.jetpackcompose.playground.domain.comment

import xyz.dudedayaworks.jetpackcompose.playground.domain.model.PostComment

interface CommentRepository {
    suspend fun getPostComments(feedPostId: Int): Result<List<PostComment>>
}