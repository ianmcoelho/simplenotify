package com.example.core

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class SimpleAppNotificationApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val channelId = "${BuildConfig.APPLICATION_ID} Channel Id"
        const val channelName = "${BuildConfig.APPLICATION_ID} Channel Name"
    }
}