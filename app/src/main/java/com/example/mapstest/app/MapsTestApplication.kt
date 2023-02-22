package com.example.mapstest.app

import android.app.Application
import com.example.mapstest.app.di.AppComponent

class MapsTestApplication : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

    }

}