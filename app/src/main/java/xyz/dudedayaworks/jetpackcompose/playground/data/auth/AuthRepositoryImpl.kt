package xyz.dudedayaworks.jetpackcompose.playground.data.auth

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.AuthRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.NotAuthenticated

class AuthRepositoryImpl : AuthRepository {
    private var token = ""
    private val _authenticatedState = MutableStateFlow(false)
    override val authenticatedState: StateFlow<Boolean>
        get() = _authenticatedState

    override suspend fun authenticate(username: String, password: String): Result<Unit> {
        delay(1500L)
        token = "someToken"
        _authenticatedState.emit(true)
        return Result.success(Unit)
    }

    override suspend fun getToken(): Result<String> {
        delay(200L)
        return if (token.isNotBlank()) {
            Result.success(token)
        } else {
            Result.failure(NotAuthenticated)
        }
    }
}