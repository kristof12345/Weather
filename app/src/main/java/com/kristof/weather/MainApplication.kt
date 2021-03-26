package com.kristof.weather

import android.app.Application
import com.kristof.weather.presenters.PresenterModule

class MainApplication : Application() {
    lateinit var injector: DIComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerDIComponent.builder()
            .presenterModule(PresenterModule())
            .build()
    }
}