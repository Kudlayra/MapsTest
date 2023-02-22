package com.example.mapstest.app.presentation.ui.view_models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MainViewModel : ViewModel() {

    private val _currentLocation = MutableLiveData<LatLng>()
    val currentLocation
    get() = _currentLocation.value

    fun setCurrentLocation(location: LatLng) {
        _currentLocation.postValue(location)
    }

}