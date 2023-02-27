package com.example.mapstest.data.api

class RemoteDataSource(private val service: GeocoderService) {

    suspend fun getGeocoder(geocode: String) = service.getData(geocode = geocode)

}