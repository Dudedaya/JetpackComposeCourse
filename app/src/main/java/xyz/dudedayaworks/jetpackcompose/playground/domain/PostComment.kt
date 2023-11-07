package xyz.dudedayaworks.jetpackcompose.playground.domain

import xyz.dudedayaworks.jetpackcompose.playground.R

data class PostComment(
    val id: Int,
    val authorName: String,
    val createdAt: String,
    val message: String,
    val avatarResId: Int,
) {
    companion object {
        fun preview(id: Int) = PostComment(
            id = id,
            authorName = "AuthorName",
            createdAt = "14:00",
            message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            avatarResId = R.drawable.avatar2,
        )
    }
}