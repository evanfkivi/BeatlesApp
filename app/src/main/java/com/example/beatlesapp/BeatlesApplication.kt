package com.example.beatlesapp

import android.app.Application
import com.example.beatlesapp.data.AppContainer
import com.example.beatlesapp.data.DefaultAppContainer

class BeatlesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
