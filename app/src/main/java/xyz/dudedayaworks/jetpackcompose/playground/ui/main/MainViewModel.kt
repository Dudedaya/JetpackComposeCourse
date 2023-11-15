package xyz.dudedayaworks.jetpackcompose.playground.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.PlaygroundApp
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.AuthRepository

class MainViewModel(
    private val authRepository: AuthRepository = PlaygroundApp.authRepository,
) : ViewModel() {

    private val _screenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    internal val screenState: StateFlow<MainScreenState>
        get() = _screenState

    init {
        viewModelScope.launch {
            authRepository.authenticatedState.map {
                if (it) {
                    MainScreenState.Authenticated
                } else {
                    MainScreenState.NotAuthenticated
                }
            }.collect(_screenState)
        }
    }
}