package com.github.daneko.tubotan

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.github.daneko.tubotan.ui.theme.TubotanTheme

class DebugMenuActivity : ComponentActivity() {
    companion object {
        fun showDebugMenuOnNotification(context: Context) {
            val manager = NotificationManagerCompat.from(context)
            val channelId = "debug_menu"
            manager.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    channelId,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT,
                )
                    .setName("HSJ-DebugMenu")
                    .setShowBadge(false)
                    .build()
            )

            val pendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                0x1001,
                Intent(context, DebugMenuActivity::class.java),
                pendingIntentFlag,
            )

            val notification = NotificationCompat.Builder(context, channelId)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentTitle("Tubotan Debug Menu")
                .setContentText("support your debug")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_note_alt_small)
                .setColor(ContextCompat.getColor(context, R.color.debug_primary_color))
                .setOngoing(true)
                .setAutoCancel(false)
                .build()

            manager.notify(0, notification)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TubotanTheme {
                AllTextStyleScreen()
            }
        }
    }
}
