package com.easy.geoWarden.data.local.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient{
    fun getLocationUpdate(interval:Long): Flow<Location>

    class LocationUpdateException(message: String) : Exception(message)
}
