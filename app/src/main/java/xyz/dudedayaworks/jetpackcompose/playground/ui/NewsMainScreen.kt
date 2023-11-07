package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val selectedNavItem by viewModel.selectedNavItem.collectAsState()
    Scaffold(
        bottomBar = {
            MainBottomBar(
                selectedNavItem = selectedNavItem,
                navigationItems = viewModel.navigationItems,
                onNavItemSelected = viewModel::onNavItemSelected,
            )
        }
    ) { paddingValues ->
        when (selectedNavItem) {
            NavigationItem.Favorites -> Text(text = "Favorites")
            NavigationItem.Home -> HomeScreen(viewModel = viewModel, paddingValues = paddingValues)
            NavigationItem.Profile -> Text(text = "Profile")
        }
    }
}

@Composable
private fun MainBottomBar(
    selectedNavItem: NavigationItem,
    navigationItems: List<NavigationItem>,
    onNavItemSelected: (NavigationItem) -> Unit,
) {
    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = selectedNavItem == item,
                onClick = { onNavItemSelected(item) },
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