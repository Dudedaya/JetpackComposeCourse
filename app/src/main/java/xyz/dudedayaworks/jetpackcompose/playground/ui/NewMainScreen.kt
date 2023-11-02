package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { MainBottomBar() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Text(text = "MainScreen")
        }
    }
}

@Composable
private fun MainBottomBar() {
    val selectionState = remember {
        mutableStateOf(0)
    }
    val icons = listOf(
        Icons.Outlined.Home to Icons.Filled.Home,
        Icons.Outlined.Favorite to Icons.Filled.Favorite,
        Icons.Outlined.Person to Icons.Filled.Person,
    )
    NavigationBar {
        icons.forEachIndexed { index, (iconUnselected, iconSelected) ->
            val isSelected = selectionState.value == index
            val name = iconUnselected.name.split(".").last()
            NavigationBarItem(
                selected = isSelected,
                onClick = { selectionState.value = index },
                icon = {
                    Icon(
                        imageVector = if (isSelected) iconSelected else iconUnselected,
                        contentDescription = name,
                    )
                },
                label = { Text(text = name) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                ),
            )
        }
    }
}