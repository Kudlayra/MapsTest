package com.example.mapstest.app.di

import com.example.mapstest.app.presentation.ui.view_models.MainViewModelFactory
import com.example.mapstest.utils.MapUtils
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(mapUtils: MapUtils): MainViewModelFactory {
        return MainViewModelFactory(mapUtils)
    }

}