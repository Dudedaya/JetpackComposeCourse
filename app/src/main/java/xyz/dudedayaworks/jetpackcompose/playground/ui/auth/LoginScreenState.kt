package xyz.dudedayaworks.jetpackcompose.playground.ui.auth

internal sealed class LoginScreenState {
    object Loading : LoginScreenState()
    object NotAuthorized : LoginScreenState()
    object Authorized : LoginScreenState()
    data class AuthError(val errorMessage: String) : LoginScreenState()
}