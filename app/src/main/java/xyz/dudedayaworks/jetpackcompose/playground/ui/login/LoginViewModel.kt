package xyz.dudedayaworks.jetpackcompose.playground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.PlaygroundApp
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.AuthRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.NotAuthenticated

class LoginViewModel(
    private val authRepository: AuthRepository = PlaygroundApp.authRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Loading)
    internal val state: StateFlow<LoginScreenState>
        get() = _state

    init {
        viewModelScope.launch {
            authRepository.getToken()
                .onSuccess {
                    _state.emit(LoginScreenState.Authenticated)
                }
                .onFailure {
                    if (it == NotAuthenticated) {
                        _state.emit(LoginScreenState.NotAuthenticated)
                    } else {
                        _state.emit(LoginScreenState.AuthError(it.message ?: "Unknown error"))
                    }
                }
        }
    }

    fun onLogin(username: String, password: String) {
        viewModelScope.launch {
            _state.emit(LoginScreenState.Loading)
            authRepository.authenticate(username, password)
                .onSuccess {
                    _state.emit(LoginScreenState.Authenticated)
                }
                .onFailure {
                    _state.emit(LoginScreenState.AuthError(it.message ?: "Unknown error"))
                }
        }
    }
}