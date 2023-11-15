package xyz.dudedayaworks.jetpackcompose.playground.domain.models

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import xyz.dudedayaworks.jetpackcompose.playground.R

@Parcelize
data class FeedPost(
    val id: Int,
    val title: String,
    val createdAt: String,
    val message: String,
    val imageResId: Int,
    val avatarResId: Int,
    val statistics: List<StatisticItem>,
) : Parcelable {
    companion object {
        fun preview(id: Int) = FeedPost(
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

        @Suppress("DEPRECATION")
        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {
            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
