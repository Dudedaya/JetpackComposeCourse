package xyz.dudedayaworks.jetpackcompose.playground.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.dudedayaworks.jetpackcompose.playground.data.auth.AuthRepository
import xyz.dudedayaworks.jetpackcompose.playground.data.auth.NotAuthenticated

class LoginViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Loading)
    internal val state: StateFlow<LoginScreenState>
        get() = _state

    init {
        viewModelScope.launch {
            authRepository.getToken()
                .onSuccess {
                    _state.emit(LoginScreenState.Authorized)
                }
                .onFailure {
                    if (it == NotAuthenticated) {
                        _state.emit(LoginScreenState.NotAuthorized)
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
                    _state.emit(LoginScreenState.Authorized)
                }
                .onFailure {
                    _state.emit(LoginScreenState.AuthError(it.message ?: "Unknown error"))
                }
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun factory(authRepository: AuthRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(authRepository) as T
                }
            }
        }
    }
}