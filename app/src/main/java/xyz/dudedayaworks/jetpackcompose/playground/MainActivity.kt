package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.ui.InstagramHeader
import xyz.dudedayaworks.jetpackcompose.playground.ui.MainViewModel
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<MainViewModel>()
        setContent {
            CardListTest(viewModel = viewModel)
        }
    }
}

@Composable
private fun CardListTest(viewModel: MainViewModel) {
    val items = viewModel.items.collectAsState()
    val onClick = viewModel::toggleFollowing
    val scope = rememberCoroutineScope()
    JetpackComposePlaygroundTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
            ) {
                items(items.value) {
                    InstagramHeader(model = it, onClick = onClick)
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(48.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                }
            ) {
                Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = null)
            }
        }
    }
}