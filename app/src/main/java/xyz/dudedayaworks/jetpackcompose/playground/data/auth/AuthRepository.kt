package xyz.dudedayaworks.jetpackcompose.playground.data.auth

interface AuthRepository {
    suspend fun authenticate(username: String, password: String): Result<Unit>
    suspend fun getToken(): Result<String>
}