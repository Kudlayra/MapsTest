package com.example.mapstest.app.di

import com.example.mapstest.app.presentation.ui.MainFragment
import com.example.mapstest.app.presentation.ui.MapFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(mapFragment: MapFragment)

}