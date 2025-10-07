package com.example.beatlesapp.data

import android.app.Application

class BeatlesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
