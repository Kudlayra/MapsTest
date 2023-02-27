package com.example.mapstest.data

import com.example.mapstest.data.api.RemoteDataSource

class DataRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getGeocoder(geocode: String) = remoteDataSource.getGeocoder(geocode = geocode)

}