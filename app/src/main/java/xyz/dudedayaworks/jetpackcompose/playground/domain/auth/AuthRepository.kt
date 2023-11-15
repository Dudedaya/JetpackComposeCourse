package xyz.dudedayaworks.jetpackcompose.playground.domain.auth

import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    val authenticatedState: StateFlow<Boolean>
    suspend fun authenticate(username: String, password: String): Result<Unit>
    suspend fun getToken(): Result<String>
}