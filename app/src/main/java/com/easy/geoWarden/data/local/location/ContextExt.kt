package com.easy.geoWarden.data.local.location

import android.content.Context
import android.content.pm.PackageManager

fun Context.hasLocationPermission(): Boolean {
    return checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}