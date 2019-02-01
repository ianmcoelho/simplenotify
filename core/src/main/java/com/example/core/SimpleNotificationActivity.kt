package com.example.core

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.simple_notification_button_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onClick

class SimpleNotificationActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_notification_button_activity)
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        buttonCancelNotifications.onClick {
            cancel = true
        }

        buttonTriggerNotification.setOnClickListener {
            cancel = false
            doAsync {
                for(i in 0..editTextNotificationCount.text.toString().toInt()) {
                    if(cancel)
                        return@doAsync

                    val notification  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Notification.Builder(this@SimpleNotificationActivity, SimpleAppNotificationApplication.channelId)
                    } else {
                        Notification.Builder(this@SimpleNotificationActivity)
                    }
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle("${getString(R.string.app_name)} $curNotificationId")
                        .setVibrate(longArrayOf(100, 200))
                        .setLights(Color.BLUE, 200, 10000)
                        .setPriority(Notification.PRIORITY_MAX)
                        .build()

                        manager.notify(curNotificationId++, notification)

                        Thread.sleep(editTextDelayTime.text.toString().toLong())
                }
            }
        }
    }

    companion object {
        @Volatile
        private var cancel = false

        @Volatile
        var curNotificationId = 0
    }
}
