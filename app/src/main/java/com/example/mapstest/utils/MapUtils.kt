package com.example.mapstest.utils

import com.example.mapstest.data.models.Address
import com.yandex.mapkit.geometry.Point

class MapUtils {

    fun mapPointToAddress(point: Point): Address {
        return Address(point.latitude, point.longitude)
    }

}