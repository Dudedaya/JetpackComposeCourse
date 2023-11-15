package xyz.dudedayaworks.jetpackcompose.playground.ui.newsfeed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.dudedayaworks.jetpackcompose.playground.domain.model.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.ui.common.LoadingScreen

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentsClick: (FeedPost) -> Unit,
) {
    val viewModel = viewModel<NewsFeedViewModel>()
    val screenState by viewModel.screenState.collectAsState()

    when (val state = screenState) {
        is NewsFeedScreenState.Posts ->
            PostsScreen(
                paddingValues,
                posts = state.posts,
                onDelete = viewModel::onDeletePost,
                onPostStatisticClick = viewModel::onPostStatisticClick,
                onCommentsClick = onCommentsClick,
            )

        NewsFeedScreenState.Loading ->
            LoadingScreen(paddingValues)
    }
}