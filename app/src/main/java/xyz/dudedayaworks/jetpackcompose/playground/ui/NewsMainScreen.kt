package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import xyz.dudedayaworks.jetpackcompose.playground.navigation.AppNavGraph
import xyz.dudedayaworks.jetpackcompose.playground.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val navigationItems = listOf(
                NavigationItem.Home,
                NavigationItem.Favorites,
                NavigationItem.Profile,
            )
            MainBottomBar(
                currentRoute = currentRoute,
                navigationItems = navigationItems,
                onNavItemSelected = {
                    navHostController.navigate(it.screen.route) {
                        launchSingleTop = true
                        popUpTo(Screen.NewsFeed.route) {
                            saveState = true
                        }
                        restoreState = true
                    }
                },
            )
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                )
            },
            favoriteScreenContent = { StatefulText(text = "Favorites") },
            profileScreenContent = { StatefulText(text = "Profile") })
    }
}

@Composable
private fun StatefulText(text: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { count++ },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$text count:$count",
            fontSize = 30.sp,
        )
    }
}

@Composable
private fun MainBottomBar(
    currentRoute: String?,
    navigationItems: List<NavigationItem>,
    onNavItemSelected: (NavigationItem) -> Unit,
) {
    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
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