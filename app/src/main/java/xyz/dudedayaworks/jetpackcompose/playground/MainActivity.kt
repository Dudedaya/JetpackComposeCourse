package xyz.dudedayaworks.jetpackcompose.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import xyz.dudedayaworks.jetpackcompose.playground.data.auth.AuthRepositoryImpl
import xyz.dudedayaworks.jetpackcompose.playground.ui.auth.AuthScreen
import xyz.dudedayaworks.jetpackcompose.playground.ui.auth.LoginViewModel
import xyz.dudedayaworks.jetpackcompose.playground.ui.theme.JetpackComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePlaygroundTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
//                    MainScreen()
                    // TODO LESSON_7.2 14.11.2023 22:08: Move viewModel creation out
                    val loginViewModel by viewModels<LoginViewModel> {
                        LoginViewModel.factory(AuthRepositoryImpl())
                    }
                    AuthScreen(viewModel = loginViewModel)
                }
            }
        }
    }
}