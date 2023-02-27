package com.example.mapstest.app.presentation.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mapstest.domain.usecases.GetGeocoderUseCase
import com.example.mapstest.utils.MapUtils

class MainViewModelFactory(
    private val mapUtils: MapUtils,
    private val getGeocoderUseCase: GetGeocoderUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress
            return MainViewModel() as T
        }
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress
            return MapViewModel(mapUtils, getGeocoderUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}