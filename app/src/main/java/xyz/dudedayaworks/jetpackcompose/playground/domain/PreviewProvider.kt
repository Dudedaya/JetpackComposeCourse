package xyz.dudedayaworks.jetpackcompose.playground.domain

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlin.random.Random

object PreviewProvider {
    fun randomImageUrl(
        id: Int = Random.nextInt(200),
        width: Int = Random.nextInt(200, 401),
        height: Int = Random.nextInt(200, 401),
    ): String {
        return "https://picsum.photos/id/$id/$width/$height"
    }

    fun randomText(words: Int): String {
        val loremIpsum = LoremIpsum()
        return loremIpsum.values
            .first()
            .replace("\n", " ")
            .split(" ")
            .shuffled()
            .take(words)
            .joinToString(" ")
            .lowercase()
    }
}