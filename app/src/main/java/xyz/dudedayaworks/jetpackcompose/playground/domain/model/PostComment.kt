package xyz.dudedayaworks.jetpackcompose.playground.domain.model

import xyz.dudedayaworks.jetpackcompose.playground.domain.PreviewProvider
import kotlin.random.Random

data class PostComment(
    val id: Int,
    val authorName: String,
    val createdAt: String,
    val message: String,
    val avatarUrl: String,
) {
    companion object {
        @Suppress("Deprecation")
        fun preview(id: Int): PostComment {
            val random = Random(id)
            val authorName = PreviewProvider.randomText(random.nextInt(1, 3)).capitalize()
            val message = PreviewProvider.randomText(random.nextInt(2, 16)).capitalize()
            return PostComment(
                id = id,
                authorName = authorName,
                createdAt = "14:00",
                message = message,
                avatarUrl = PreviewProvider.randomImageUrl(id = id, width = 128, height = 128),
            )
        }
    }
}