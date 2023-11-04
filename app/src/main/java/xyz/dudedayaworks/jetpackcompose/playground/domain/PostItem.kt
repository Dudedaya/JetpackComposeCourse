package xyz.dudedayaworks.jetpackcompose.playground.domain

import xyz.dudedayaworks.jetpackcompose.playground.R

data class PostItem(
    val id: Int,
    val title: String,
    val createdAt: String,
    val message: String,
    val imageResId: Int,
    val avatarResId: Int,
    val statistics: List<StatisticItem>,
) {
    companion object {
        fun preview(id: Int) = PostItem(
            id = id,
            title = "Title of the post",
            createdAt = "14:00",
            message = "Some kind of post message that will be about 2 lines long. Blah, blah-blah! Blahblahblahblah, blah.",
            imageResId = R.drawable.post_image,
            avatarResId = R.drawable.avatar,
            statistics = listOf(
                StatisticItem(type = StatisticType.VIEWS, count = 206),
                StatisticItem(type = StatisticType.SHARES, count = 206),
                StatisticItem(type = StatisticType.COMMENTS, count = 11),
                StatisticItem(type = StatisticType.LIKES, count = 491),
            ),
        )
    }
}
