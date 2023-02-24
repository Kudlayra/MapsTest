package com.example.mapstest.app.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mapstest.utils.MapUtils

class MainViewModelFactory(private val mapUtils: MapUtils) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress
            return MainViewModel() as T
        }
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress
            return MapViewModel(mapUtils) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}