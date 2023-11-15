package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.dudedayaworks.jetpackcompose.playground.ui.common.LoadingScreen
import xyz.dudedayaworks.jetpackcompose.playground.ui.login.LoginScreen
import xyz.dudedayaworks.jetpackcompose.playground.ui.main.MainScreen
import xyz.dudedayaworks.jetpackcompose.playground.ui.main.MainScreenState
import xyz.dudedayaworks.jetpackcompose.playground.ui.main.MainViewModel
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                val viewModel = viewModel<MainViewModel>()
                val mainScreenState by viewModel.screenState.collectAsState()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    when (mainScreenState) {
                        MainScreenState.Loading -> {
                            LoadingScreen(paddingValues = PaddingValues())
                        }

                        MainScreenState.Authenticated -> {
                            MainScreen()
                        }

                        MainScreenState.NotAuthenticated -> {
                            LoginScreen()
                        }
                    }
                }
            }
        }
    }
}