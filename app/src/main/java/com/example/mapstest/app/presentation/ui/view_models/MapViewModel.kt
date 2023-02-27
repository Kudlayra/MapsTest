package com.example.mapstest.app.presentation.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapstest.data.models.Address
import com.example.mapstest.domain.usecases.GetGeocoderUseCase
import com.example.mapstest.utils.MapUtils
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch

class MapViewModel(
    private val mapUtils: MapUtils,
    private val getGeocoderUseCase: GetGeocoderUseCase,
) : ViewModel() {

    private val _currentLocation = MutableLiveData<Address?>()
    val currentLocation: LiveData<Address?>
        get() = _currentLocation

    private fun setCurrentLocation(addressName: String, point: Point) {
        _currentLocation.value =
            mapUtils.mapPointToAddress(point = point, addressName = addressName)
    }

    fun setCurrentLocation(address: Address) {
        _currentLocation.value = address
    }

    fun getAddress(point: Point) {
        viewModelScope.launch {
            try {
                val geocoder = "${point.longitude},${point.latitude}"
                val address = getGeocoderUseCase.invoke(geocoder)
                val addressName =
                    address?.response?.geoObjectCollection?.featureMember?.get(0)?.geoObject?.metaDataProperty?.geocoderMetaData?.text
                addressName?.let { setCurrentLocation(addressName, point) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}