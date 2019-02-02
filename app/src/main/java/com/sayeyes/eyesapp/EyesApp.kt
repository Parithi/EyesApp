package com.sayeyes.eyesapp

import android.app.Application
import com.parithi.paintora.di.appModule
import org.koin.core.context.startKoin

class EyesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}