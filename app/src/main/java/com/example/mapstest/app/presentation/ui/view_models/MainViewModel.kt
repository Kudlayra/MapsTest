package com.example.mapstest.app.presentation.ui.view_models


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapstest.data.models.Address

class MainViewModel : ViewModel() {

    private val _currentLocation = MutableLiveData<Address?>()
    val currentLocation: LiveData<Address?>
        get() = _currentLocation

    fun setCurrentLocation(address: Address) {
        _currentLocation.postValue(address)
    }

}