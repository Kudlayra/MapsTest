package com.example.mapstest.app.di

import com.example.mapstest.utils.MapUtils
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideMapUtils(): MapUtils {
        return MapUtils()
    }

}