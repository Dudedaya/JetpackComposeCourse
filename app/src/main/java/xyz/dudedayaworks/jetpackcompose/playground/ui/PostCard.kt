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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.R
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticItem
import xyz.dudedayaworks.jetpackcompose.playground.domain.StatisticType
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    postItem: PostItem,
    onStatisticsItemClick: (StatisticType) -> Unit,
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
                onClick = onStatisticsItemClick,
            )
        }
    }
}

@Composable
private fun PostHeader(postItem: PostItem) {
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
    onClick: (StatisticType) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.weight(1f)) {
            IconWithText(
                iconResId = R.drawable.ic_views,
                text = statistics.getStringCount(StatisticType.VIEWS),
            ) {
                onClick(StatisticType.VIEWS)
            }
        }
        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = statistics.getStringCount(StatisticType.SHARES),
                onClick = {
                    onClick(StatisticType.SHARES)
                }
            )
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = statistics.getStringCount(StatisticType.COMMENTS),
                onClick = {
                    onClick(StatisticType.COMMENTS)
                }
            )
            IconWithText(
                iconResId = R.drawable.ic_like_outline,
                text = statistics.getStringCount(StatisticType.LIKES),
                onClick = {
                    onClick(StatisticType.LIKES)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getStringCount(type: StatisticType): String {
    return find { it.type == type }?.count?.toString()
        ?: error("Type $type is not present in the list")
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

@Composable
@Preview(showSystemUi = true)
private fun LightPreview() {
    JetpackComposePlaygroundTheme(darkTheme = false) {
        PostCard(postItem = PostItem.PREVIEW, onStatisticsItemClick = {})
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF191919, showSystemUi = true)
private fun DarkPreview() {
    JetpackComposePlaygroundTheme(darkTheme = true) {
        PostCard(postItem = PostItem.PREVIEW, onStatisticsItemClick = {})
    }
}