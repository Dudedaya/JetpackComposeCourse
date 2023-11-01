package xyz.dudedayaworks.jetpackcompose.playground

data class PostItem(
    val title: String,
    val createdAt: String,
    val message: String,
    val imageResId: Int,
    val avatarResId: Int,
    val viewsCount: Int,
    val sharesCount: Int,
    val commentsCount: Int,
    val likesCount: Int,
    val liked: Boolean,
) {
    companion object {
        val PREVIEW = PostItem(
            title = "Title of the post",
            createdAt = "14:00",
            message = "Some kind of post message that will be about 2 lines long. Blah, blah-blah! Blahblahblahblah, blah.",
            imageResId = R.drawable.post_image,
            avatarResId = R.drawable.avatar,
            viewsCount = 206,
            sharesCount = 206,
            commentsCount = 11,
            likesCount = 491,
            liked = false,
        )
    }
}
