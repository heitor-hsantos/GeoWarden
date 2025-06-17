package com.easy.geoWarden.data.local.location

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob()+ Dispatchers.IO)
    private lateinit var locationClient: LocationClient
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocation(applicationContext,LocationServices.getFusedLocationProviderClient(applicationContext))


    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_LOCATION_SERVICE -> {
                // Start location updates
                start()
            }
            ACTION_STOP_LOCATION_SERVICE -> {
                // Stop location updates
                stop()
            }
        }
        return START_STICKY
    }
    private fun start() {
        // Start location updates
        val notification = NotificationCompat.Builder(this, "location_channel")
            .setContentTitle("Tracking Location Service")
            .setContentText("Location: null")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)

    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdate(1000)
            .catch { e ->e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString().takeLast(3)
                val long = location.longitude.toString().takeLast(3)
                val updatedNofication = notification
                    .setContentText("Location: $lat, $long")
                notificationManager.notify(1, updatedNofication.build())
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }
    private fun stop() {
        // Stop location updates
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()

    }
    override fun onDestroy() {
        super.onDestroy()
        stop()
    }



    companion object {
        const val ACTION_START_LOCATION_SERVICE = "com.easy.geoWarden.action.START_LOCATION_SERVICE"
        const val ACTION_STOP_LOCATION_SERVICE = "com.easy.geoWarden.action.STOP_LOCATION_SERVICE"
    }
}