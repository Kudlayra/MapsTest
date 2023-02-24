package com.example.mapstest.app

import android.app.Application
import com.example.mapstest.app.di.AppComponent
import com.example.mapstest.app.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory

class MapsTestApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        appComponent = DaggerAppComponent.builder().build()

    }

    companion object {
        const val MAPKIT_API_KEY = "91f7c318-a65c-46c2-965d-a9f4af7c7098"
    }
}