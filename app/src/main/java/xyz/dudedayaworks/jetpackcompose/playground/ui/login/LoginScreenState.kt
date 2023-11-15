package xyz.dudedayaworks.jetpackcompose.playground.ui.login

internal sealed class LoginScreenState {
    object Loading : LoginScreenState()
    object NotAuthenticated : LoginScreenState()
    object Authenticated : LoginScreenState()
    data class AuthError(val errorMessage: String) : LoginScreenState()
}