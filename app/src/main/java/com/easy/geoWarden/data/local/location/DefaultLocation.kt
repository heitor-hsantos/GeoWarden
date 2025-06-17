package com.easy.geoWarden.data.local.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class DefaultLocation( private val context:Context,
    private val client: FusedLocationProviderClient
    ): LocationClient {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdate(interval: Long): Flow<Location> {
        return callbackFlow {
            if(!context.hasLocationPermission()){
                throw LocationClient.LocationUpdateException("Missing location permission")
            }
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)
            if (!isGpsEnabled && !isNetworkEnabled) {
                throw LocationClient.LocationUpdateException("GPS Disabled")
            }
            val request = LocationRequest.Builder(interval).build()

            val locationCallback = object : com.google.android.gms.location.LocationCallback() {
                override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                  super.onLocationResult(locationResult)
                    locationResult.locations.lastOrNull()?.let {  location -> launch { send(location) }}
                }
            }


            client.requestLocationUpdates(request,locationCallback,Looper.getMainLooper())

            awaitClose{
                client.removeLocationUpdates(locationCallback)
            }
        }
    }

}