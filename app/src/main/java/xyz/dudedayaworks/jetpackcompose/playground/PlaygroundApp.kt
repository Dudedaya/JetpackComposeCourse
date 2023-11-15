package xyz.dudedayaworks.jetpackcompose.playground

import android.app.Application
import android.util.Log
import xyz.dudedayaworks.jetpackcompose.playground.data.auth.AuthRepositoryImpl
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.AuthRepository

class PlaygroundApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("PlaygroundApp", "FATAL EXCEPTION: $t\n${e::class.java}: ${e.message}", e)
            throw e
        }
    }

    companion object {
        private val _authRepository by lazy { AuthRepositoryImpl() }
        val authRepository: AuthRepository get() = _authRepository
    }
}