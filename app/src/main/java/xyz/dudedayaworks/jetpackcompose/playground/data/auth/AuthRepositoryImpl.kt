package xyz.dudedayaworks.jetpackcompose.playground.data.auth

import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {
    private var token = ""
    override suspend fun authenticate(username: String, password: String): Result<Unit> {
        delay(3000L)
        token = "someToken"
        return Result.success(Unit)
    }

    override suspend fun getToken(): Result<String> {
        delay(100L)
        return if (token.isNotBlank()) {
            Result.success(token)
        } else {
            Result.failure(NotAuthenticated)
        }
    }
}