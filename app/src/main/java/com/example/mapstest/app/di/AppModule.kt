package com.example.mapstest.app.di

import com.example.mapstest.app.presentation.ui.view_models.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory()
    }

}