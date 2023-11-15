package xyz.dudedayaworks.jetpackcompose.playground

import android.app.Application
import android.util.Log
import xyz.dudedayaworks.jetpackcompose.playground.data.auth.AuthRepositoryImpl
import xyz.dudedayaworks.jetpackcompose.playground.data.comment.CommentRepositoryImpl
import xyz.dudedayaworks.jetpackcompose.playground.data.post.PostRepositoryImpl
import xyz.dudedayaworks.jetpackcompose.playground.domain.auth.AuthRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.comment.CommentRepository
import xyz.dudedayaworks.jetpackcompose.playground.domain.post.PostRepository

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
        private val _postRepository by lazy { PostRepositoryImpl() }
        private val _commentRepository by lazy { CommentRepositoryImpl() }
        val authRepository: AuthRepository get() = _authRepository
        val postRepository: PostRepository get() = _postRepository
        val commentRepository: CommentRepository get() = _commentRepository
    }
}