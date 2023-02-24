package com.example.mapstest.app.presentation.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapstest.data.models.Address
import com.example.mapstest.utils.MapUtils
import com.yandex.mapkit.geometry.Point

class MapViewModel(private val mapUtils: MapUtils) : ViewModel() {

    private val _currentLocation = MutableLiveData<Address?>()
    val currentLocation: LiveData<Address?>
        get() = _currentLocation

    fun setCurrentLocation(point: Point) {
        _currentLocation.value = mapUtils.mapPointToAddress(point)
    }

    fun setCurrentLocation(address: Address) {
        _currentLocation.postValue(address)
    }

}