package com.example.mapstest.app.di

import com.example.mapstest.data.DataRepository
import com.example.mapstest.data.api.GeocoderService
import com.example.mapstest.data.api.RemoteDataSource
import com.example.mapstest.utils.MapUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object DataModule {

    private const val BASE_URL = "https://geocode-maps.yandex.ru/"

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideGeocodeService(retrofit: Retrofit) = retrofit.create(GeocoderService::class.java)

    @Provides
    fun provideMapUtils(): MapUtils {
        return MapUtils()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(geocoderService: GeocoderService) =
        RemoteDataSource(geocoderService)

    @Singleton
    @Provides
    fun provideDataRepository(remoteDataSource: RemoteDataSource) = DataRepository(remoteDataSource)

}