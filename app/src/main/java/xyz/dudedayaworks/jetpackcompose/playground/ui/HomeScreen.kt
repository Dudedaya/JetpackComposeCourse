package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val screenState by viewModel.screenState.collectAsState()

    when (val state = screenState) {
        is HomeScreenState.Comments ->
            CommentsScreen(
                paddingValues = paddingValues,
                feedPost = state.feedPost,
                postComments = state.comments,
                onNavigationBack = viewModel::onReturnToPosts,
            )

        is HomeScreenState.Posts ->
            PostsScreen(
                paddingValues,
                posts = state.posts,
                onDelete = viewModel::onDeletePost,
                onPostStatisticClick = viewModel::onPostStatisticClick,
                onCommentsClick = viewModel::onCommentsClick,
            )

        HomeScreenState.Loading ->
            LoadingScreen(paddingValues)
    }
}

@Composable
fun LoadingScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
