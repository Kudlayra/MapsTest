package com.example.mapstest.domain.usecases

import com.example.mapstest.data.DataRepository

class GetGeocoderUseCase(private val dataRepository: DataRepository) {

    suspend fun invoke(geocoder: String) = dataRepository.getGeocoder(geocoder)

}