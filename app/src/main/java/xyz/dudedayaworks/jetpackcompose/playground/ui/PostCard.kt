package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.PostItem
import xyz.dudedayaworks.jetpackcompose.playground.R
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

@Composable
fun PostCard(postItem: PostItem) {
    Card(
        modifier = Modifier.wrapContentSize(),
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
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = postItem.imageResId),
                contentDescription = "post",
                contentScale = ContentScale.FillWidth,
            )
            PostFooter(postItem)
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
private fun PostFooter(postItem: PostItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = postItem.viewsCount.toString(),
            fontFamily = FontFamily.Monospace,
            color = Color.Gray,
        )
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = R.drawable.ic_views),
            contentDescription = "views",
            colorFilter = ColorFilter.tint(color = Color.Gray),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = postItem.sharesCount.toString(),
            fontFamily = FontFamily.Monospace,
            color = Color.Gray,
        )
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "reposts",
            colorFilter = ColorFilter.tint(color = Color.Gray),
        )
        Text(
            text = postItem.commentsCount.toString(),
            fontFamily = FontFamily.Monospace,
            color = Color.Gray,
        )
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = "comments",
            colorFilter = ColorFilter.tint(color = Color.Gray),
        )
        Text(
            text = postItem.likesCount.toString(),
            fontFamily = FontFamily.Monospace,
            color = Color.Gray,
        )
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = if (postItem.liked) R.drawable.ic_like_fill else R.drawable.ic_like_outline),
            contentDescription = "likes",
            colorFilter = ColorFilter.tint(color = Color.Gray),
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun LightPreview() {
    JetpackComposePlaygroundTheme(darkTheme = false) {
        PostCard(postItem = PostItem.PREVIEW)
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF191919, showSystemUi = true)
private fun DarkPreview() {
    JetpackComposePlaygroundTheme(darkTheme = true) {
        PostCard(postItem = PostItem.PREVIEW)
    }
}