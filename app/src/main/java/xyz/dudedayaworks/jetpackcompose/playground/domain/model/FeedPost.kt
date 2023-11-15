package xyz.dudedayaworks.jetpackcompose.playground.domain.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import xyz.dudedayaworks.jetpackcompose.playground.domain.PreviewProvider
import kotlin.random.Random

@Parcelize
data class FeedPost(
    val id: Int,
    val title: String,
    val createdAt: String,
    val message: String,
    val imageUrl: String,
    val avatarUrl: String,
    val statistics: List<StatisticItem>,
) : Parcelable {
    companion object {
        @Suppress("Deprecation")
        fun preview(id: Int): FeedPost {
            val random = Random(id)
            val title = PreviewProvider.randomText(random.nextInt(1, 4)).capitalize()
            val message = PreviewProvider.randomText(random.nextInt(3, 25)).capitalize()
            return FeedPost(
                id = id,
                title = title,
                createdAt = "14:00",
                message = message,
                imageUrl = PreviewProvider.randomImageUrl(id = id * 10),
                avatarUrl = PreviewProvider.randomImageUrl(
                    id = id * 10 + 150,
                    width = 128,
                    height = 128
                ),
                statistics = listOf(
                    StatisticItem(type = StatisticType.VIEWS, count = random.nextInt(1000)),
                    StatisticItem(type = StatisticType.SHARES, count = random.nextInt(1000)),
                    StatisticItem(type = StatisticType.COMMENTS, count = random.nextInt(1000)),
                    StatisticItem(type = StatisticType.LIKES, count = random.nextInt(1000)),
                ),
            )
        }

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
