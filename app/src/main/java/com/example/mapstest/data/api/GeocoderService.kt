package com.example.mapstest.data.api

import GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocoderService {

    @GET("/1.x")
    suspend fun getData(
        @Query("apikey") apikey: String? = GEOCODER_API_KEY,
        @Query("geocode") geocode: String?,
        @Query("format") format: String? = GEOCODER_RESPONSE_FORMAT,
    ): GeoResponse?

    companion object {
        const val GEOCODER_API_KEY = "300af89d-f7a7-4a8d-9a79-9f285a662d87"
        const val GEOCODER_RESPONSE_FORMAT = "json"
    }

}