package xyz.dudedayaworks.jetpackcompose.playground.data.comment

import kotlinx.coroutines.delay
import xyz.dudedayaworks.jetpackcompose.playground.domain.comment.CommentRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.PostComment
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class CommentRepositoryImpl : CommentRepository {
    private val cache = ConcurrentHashMap<Int, List<PostComment>>()
    override suspend fun getPostComments(
        feedPostId: Int,
    ): Result<List<PostComment>> {
        val cacheResult = cache[feedPostId]
        return if (cacheResult != null) {
            Result.success(cacheResult)
        } else {
            delay(1000L)
            val comments = List(Random.nextInt(1, 51)) {
                PostComment.preview(it)
            }
            cache[feedPostId] = comments
            Result.success(comments)
        }
    }
}