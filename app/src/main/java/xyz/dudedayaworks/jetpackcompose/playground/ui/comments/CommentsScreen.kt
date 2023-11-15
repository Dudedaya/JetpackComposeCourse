package xyz.dudedayaworks.jetpackcompose.playground.ui.comments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.dudedayaworks.jetpackcompose.playground.domain.models.FeedPost
import xyz.dudedayaworks.jetpackcompose.playground.domain.models.PostComment
import xyz.dudedayaworks.jetpackcompose.playground.ui.common.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    paddingValues: PaddingValues,
    feedPost: FeedPost,
    onNavigationBack: () -> Unit,
) {
    val viewModel = viewModel<CommentsViewModel>(factory = CommentsViewModelFactory(feedPost))
    BackHandler {
        onNavigationBack()
    }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            AppBar(
                feedPostId = feedPost.id,
                feedPostTitle = feedPost.title,
                onNavigationBack = onNavigationBack
            )
        },
    ) { appBarPadding ->
        val screenState = viewModel.screenState.collectAsState()
        when (val currentState = screenState.value) {
            CommentsScreenState.Loading -> LoadingScreen(paddingValues = paddingValues)
            is CommentsScreenState.Comments ->
                LazyColumn(
                    modifier = Modifier.padding(appBarPadding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(currentState.comments) {
                        PostCommentItem(it)
                    }
                }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar(feedPostId: Int, feedPostTitle: String, onNavigationBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier.shadow(8.dp),
        title = { Text(text = "$feedPostTitle: $feedPostId") },
        navigationIcon = {
            IconButton(onClick = onNavigationBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Composable
private fun PostCommentItem(postComment: PostComment = PostComment.preview(0)) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
            painter = painterResource(id = postComment.avatarResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = "${postComment.authorName} commentId: ${postComment.id}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = postComment.message,
                lineHeight = 18.sp,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = postComment.createdAt,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}