package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.R
import xyz.dudedayaworks.jetpackcompose.playground.domain.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    postItem: FeedPost,
    onViewsClick: (FeedPost, StatisticItem) -> Unit,
    onSharesClick: (FeedPost, StatisticItem) -> Unit,
    onCommentsClick: (FeedPost, StatisticItem) -> Unit,
    onLikesClick: (FeedPost, StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier.wrapContentSize(),
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
        ) {
            PostHeader(postItem)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = postItem.message,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = postItem.imageResId),
                contentDescription = "post",
                contentScale = ContentScale.FillWidth,
            )
            Statistics(
                statistics = postItem.statistics,
                onViewsClick = { onViewsClick(postItem, it) },
                onSharesClick = { onSharesClick(postItem, it) },
                onCommentsClick = { onCommentsClick(postItem, it) },
                onLikesClick = { onLikesClick(postItem, it) },
            )
        }
    }
}

@Composable
private fun PostHeader(postItem: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            painter = painterResource(id = postItem.avatarResId),
            contentDescription = "avatar",
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(text = postItem.title, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = postItem.createdAt,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "more",
            tint = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onViewsClick: (StatisticItem) -> Unit,
    onSharesClick: (StatisticItem) -> Unit,
    onCommentsClick: (StatisticItem) -> Unit,
    onLikesClick: (StatisticItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.weight(1f)) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views,
                text = viewsItem.count.toString(),
                onClick = {
                    onViewsClick(viewsItem)
                }
            )
        }
        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onClick = {
                    onSharesClick(sharesItem)
                }
            )
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentsItem.count.toString(),
                onClick = {
                    onCommentsClick(commentsItem)
                }
            )
            val likeItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_like_outline,
                text = likeItem.count.toString(),
                onClick = {
                    onLikesClick(likeItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return find { it.type == type } ?: error("Type $type is not present in the list")
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = text,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}