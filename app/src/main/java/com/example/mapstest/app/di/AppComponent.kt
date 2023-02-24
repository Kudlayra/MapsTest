package com.example.mapstest.app.di

import com.example.mapstest.app.presentation.ui.MainFragment
import com.example.mapstest.app.presentation.ui.MapFragment
import dagger.Component


@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(mapFragment: MapFragment)

}