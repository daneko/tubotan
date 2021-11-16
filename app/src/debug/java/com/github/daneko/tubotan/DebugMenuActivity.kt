package com.github.daneko.tubotan

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.github.daneko.tubotan.ui.theme.TubotanTheme
import androidx.compose.material.MaterialTheme as OldMaterialTheme

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
                .setContentTitle("HSJ Debug Menu")
                .setContentText("support your debug")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_note_alt_small)
                .setColor(ContextCompat.getColor(context, R.color.primary_pink))
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

@Preview
@Composable
fun AllTextStyleScreen() {
    val scrollState = rememberScrollState()
    val oldTextStyles = OldMaterialTheme.typography.run {
        listOf(
            "h1" to h1,
            "h2" to h2,
            "h3" to h3,
            "h4" to h4,
            "h5" to h5,
            "h6" to h6,
            "subtitle1" to subtitle1,
            "subtitle2" to subtitle2,
            "empty" to subtitle2,
            "body1" to body1,
            "body2" to body2,
            "empty" to body2,
            "button" to button,
            "caption" to caption,
            "overline" to overline,
        )
    }
    val newTextStyles = MaterialTheme.typography.run {
        listOf(
            "displayLarge" to displayLarge,
            "displayMedium" to displayMedium,
            "displaySmall" to displaySmall,
            "headlineLarge" to headlineLarge,
            "headlineMedium" to headlineMedium,
            "headlineSmall" to headlineSmall,
            "titleLarge" to titleLarge,
            "titleMedium" to titleMedium,
            "titleSmall" to titleSmall,
            "bodyLarge" to bodyLarge,
            "bodyMedium" to bodyMedium,
            "bodySmall" to bodySmall,
            "labelLarge" to labelLarge,
            "labelMedium" to labelMedium,
            "labelSmall" to labelSmall,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical,
            )
    ) {
        oldTextStyles.zip(newTextStyles).forEach { (old, new) ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.weight(0.5f)
                ) {
                    ShowTextStyle(old.first, old.second)
                }
                Box(
                    modifier = Modifier.weight(0.5f)
                ) {
                    ShowTextStyle(new.first, new.second)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = android.graphics.Color.WHITE.toLong(),
)
@Composable
fun ShowTextStyle(
    name: String = "preview",
    src: TextStyle = TextStyle.Default,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.4f),
        ) {
            Text(
                text = name,
                style = src,
                maxLines = 1,
            )
            Text(
                text = name,
                maxLines = 2,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(0.6f)
        ) {
            Text("size: ${src.fontSize}")
            Text("weight: ${src.fontWeight}")
            Text("line height: ${src.lineHeight}")
            Text("letter spacing: ${src.letterSpacing}")
        }
    }
}
