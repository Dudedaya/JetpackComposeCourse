package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestScaffold()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showSystemUi = true)
    @Composable
    private fun TestScaffold() {
        val drawerState = remember {
            DrawerState(
                initialValue = DrawerValue.Closed
            )
        }
        TestNavigationDrawer(drawerState) {
            Scaffold(
                topBar = {
                    TestTopAppBar()
                },
                bottomBar = { TestBottomNavigation() },
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Hello World!",
                        color = Color.Gray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    @Composable
    private fun TestBottomAppBar() {
        BottomAppBar {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                }
            }
        }
    }

    @Composable
    private fun TestBottomNavigation() {
        NavigationBar {
            val icons = listOf(
                Icons.Default.Favorite,
                Icons.Default.Home,
                Icons.Default.Person,
                Icons.Default.Settings
            )
            icons.forEachIndexed { index, icon ->
                NavigationBarItem(selected = index == 0,
                    onClick = { /*TODO*/ },
                    label = { Text(icon.name.split(".").last()) },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(imageVector = icon, contentDescription = null)
                    })
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TestTopAppBar() {
        TopAppBar(title = { Text(text = "TopAppBarTitle") }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        })
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TestNavigationDrawer(
        drawerState: DrawerState,
        content: @Composable () -> Unit,
    ) {
        val icons = listOf(
            Icons.Default.Favorite, Icons.Default.Home, Icons.Default.Person, Icons.Default.Settings
        )
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column {
                        Text(text = "Content")
                        icons.forEachIndexed { index, icon ->
                            NavigationDrawerItem(
                                label = { Text(icon.name.split(".").last()) },
                                selected = index == 0,
                                onClick = { /*TODO*/ },
                                icon = { Icon(imageVector = icon, contentDescription = null) },
                            )
                        }
                    }
                }
            },
        ) {
            content()
        }
    }
}