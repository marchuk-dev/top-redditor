package com.example.topredditor

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TopRedditorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("TopRedditorApplication on create")
    }
}