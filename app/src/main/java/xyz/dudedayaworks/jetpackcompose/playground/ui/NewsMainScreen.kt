package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dudedayaworks.jetpackcompose.playground.domain.PostItem

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    val postItemState = remember {
        mutableStateOf(PostItem.PREVIEW)
    }
    Scaffold(
        bottomBar = {
            MainBottomBar(
                navigationItems = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorites,
                    NavigationItem.Profile,
                ),
                default = NavigationItem.Home,
            )
        }
    ) { paddingValues ->
        PostCard(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp),
            postItem = postItemState.value,
            onStatisticsItemClick = { statisticType ->
                val newStatistics = postItemState.value.statistics.map { item ->
                    if (item.type == statisticType) {
                        item.copy(count = item.count + 1)
                    } else {
                        item
                    }
                }
                postItemState.value = postItemState.value.copy(statistics = newStatistics)
            },
        )
    }
}

@Composable
private fun MainBottomBar(navigationItems: List<NavigationItem>, default: NavigationItem) {
    val selectionState = remember {
        mutableStateOf(default)
    }
    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = selectionState.value == item,
                onClick = { selectionState.value = item },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(text = stringResource(id = item.titleResId)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                ),
            )
        }
    }
}