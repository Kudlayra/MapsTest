package com.example.mapstest.app.di

import com.example.mapstest.data.DataRepository
import com.example.mapstest.domain.usecases.GetGeocoderUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetGeocoderUseCase(dataRepository: DataRepository) =
        GetGeocoderUseCase(dataRepository)

}