package xyz.dudedayaworks.jetpackcompose.playground

import android.app.Application
import android.util.Log

class PlaygroundApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e("PlaygroundApp", "Fatal error at thread: $t: ${e.message}", e)
            throw e
        }
    }
}