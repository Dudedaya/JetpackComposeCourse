package xyz.dudedayaworks.jetpackcompose.playground.domain.post

import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost

interface PostRepository {
    suspend fun getPosts(): Result<List<FeedPost>>
}