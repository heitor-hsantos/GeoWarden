package com.easy.geoWarden


import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.easy.geoWarden.ui.Navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.Forest.plant(Timber.DebugTree())

        val channel = NotificationChannel(
            "geoWarden",
            "Geo Warden Notifications",
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.createNotificationChannel(channel)


        enableEdgeToEdge()
        setContent {

            NavigationGraph(modifier = Modifier.Companion)

        }

    }
}