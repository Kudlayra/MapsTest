package com.example.mapstest.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(val latitude: Double, val longitude: Double) : Parcelable
