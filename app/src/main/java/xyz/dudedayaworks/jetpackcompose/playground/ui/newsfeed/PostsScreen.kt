package xyz.dudedayaworks.jetpackcompose.playground.ui.newsfeed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.domain.models.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.models.StatisticItem

@Composable
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
fun PostsScreen(
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onDelete: (FeedPost) -> Unit,
    onPostStatisticClick: (FeedPost, StatisticItem) -> Unit,
    onCommentsClick: (FeedPost) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(posts, key = { it.id }) {
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                onDelete(it)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart),
            ) {
                PostCard(
                    postItem = it,
                    onViewsClick = onPostStatisticClick,
                    onSharesClick = onPostStatisticClick,
                    onCommentsClick = onCommentsClick,
                    onLikesClick = onPostStatisticClick,
                )
            }
        }
    }
}