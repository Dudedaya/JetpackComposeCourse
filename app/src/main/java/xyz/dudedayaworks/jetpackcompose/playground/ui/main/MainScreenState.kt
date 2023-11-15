package xyz.dudedayaworks.jetpackcompose.playground.ui.main

internal sealed class MainScreenState {
    object Loading : MainScreenState()
    object NotAuthenticated : MainScreenState()
    object Authenticated : MainScreenState()
}