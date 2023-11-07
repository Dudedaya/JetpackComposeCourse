package xyz.dudedayaworks.jetpackcompose.playground.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val itemsState = viewModel.items.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(itemsState.value, key = { it.id }) {
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.onDelete(it)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart),
            ) {
                PostCard(
                    postItem = it,
                    onViewsClick = viewModel::onPostStatisticClick,
                    onSharesClick = viewModel::onPostStatisticClick,
                    onCommentsClick = viewModel::onPostStatisticClick,
                    onLikesClick = viewModel::onPostStatisticClick,
                )
            }
        }
    }
}