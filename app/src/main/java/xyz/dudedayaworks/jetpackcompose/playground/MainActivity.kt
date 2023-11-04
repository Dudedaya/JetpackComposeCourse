package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
    JetpackComposePlaygroundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface,
        ) {
            LazyColumn {
                items(items.value) {
                    InstagramHeader(model = it, onClick = onClick)
                }
            }
        }
    }
}