package xyz.dudedayaworks.jetpackcompose.playground.data.post

import kotlinx.coroutines.delay
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.post.PostRepository

class PostRepositoryImpl : PostRepository {
    private var cache = emptyList<FeedPost>()
    override suspend fun getPosts(): Result<List<FeedPost>> {
        return if (cache.isEmpty()) {
            delay(1000L)
            val feedPosts = List(10) {
                FeedPost.preview(it)
            }
            cache = feedPosts
            Result.success(feedPosts)
        } else {
            Result.success(cache)
        }
    }
}