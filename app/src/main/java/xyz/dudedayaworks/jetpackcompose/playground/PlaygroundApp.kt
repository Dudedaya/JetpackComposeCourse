package xyz.dudedayaworks.jetpackcompose.playground

import android.app.Application
import android.util.Log

class PlaygroundApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("PlaygroundApp", "FATAL EXCEPTION: $t\n${e::class.java}: ${e.message}", e)
            throw e
        }
    }
}