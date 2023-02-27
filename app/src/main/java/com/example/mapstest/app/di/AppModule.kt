package com.example.mapstest.app.di

import com.example.mapstest.app.presentation.ui.view_models.MainViewModelFactory
import com.example.mapstest.domain.usecases.GetGeocoderUseCase
import com.example.mapstest.utils.MapUtils
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(
        mapUtils: MapUtils,
        getGeocoderUseCase: GetGeocoderUseCase,
    ): MainViewModelFactory {
        return MainViewModelFactory(mapUtils, getGeocoderUseCase)
    }

}