package com.example.mapstest.utils

import com.example.mapstest.data.models.Address
import com.yandex.mapkit.geometry.Point

class MapUtils {

    fun mapPointToAddress(point: Point, addressName: String): Address {
        return Address(addressName = addressName,
            latitude = point.latitude,
            longitude = point.longitude)
    }

}