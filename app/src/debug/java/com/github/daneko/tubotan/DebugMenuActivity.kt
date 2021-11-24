package com.github.daneko.tubotan

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.github.daneko.tubotan.ui.components.TabContent
import com.github.daneko.tubotan.ui.theme.TubotanTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class DebugMenuActivity : ComponentActivity() {
    companion object {
        fun showDebugMenuOnNotification(applicationContext: Context) {
            val manager = NotificationManagerCompat.from(applicationContext)
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
                applicationContext,
                0x1001,
                Intent(applicationContext, DebugMenuActivity::class.java),
                pendingIntentFlag,
            )

            val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentTitle("Tubotan Debug Menu")
                .setContentText("support your debug")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_note_alt_small)
                .setColor(ContextCompat.getColor(applicationContext, R.color.debug_primary_color))
                .setOngoing(true)
                .setAutoCancel(false)
                .build()

            manager.notify(0, notification)
        }

        fun setDynamicShortcut(applicationContext: Context) {
            val intent =
                Intent(Intent.ACTION_VIEW, null, applicationContext, DebugMenuActivity::class.java)
            val shortcut = ShortcutInfoCompat.Builder(applicationContext, "debug_menu_shortcut")
                .setIcon(IconCompat.createWithResource(applicationContext, R.drawable.ic_note_alt))
                .setIntent(intent)
                .setShortLabel("debug menu")
                .setLongLabel("Show DebugMenuActivity")
                .build()
            ShortcutManagerCompat.addDynamicShortcuts(applicationContext, listOf(shortcut))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TubotanTheme {
                ShowAllDebugMenuScreen()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun ShowAllDebugMenuScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    TabContent(
        contents = listOf("Text", "Color"),
        contentToTitle = { it },
        pagerState = pagerState,
        onClickTab = { pageIndex ->
            scope.launch {
                pagerState.animateScrollToPage(pageIndex)
            }
        },
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (page) {
                0 -> {
                    AllTextStyleScreen()
                }
                1 -> {
                    AllColorSchemeScreen()
                }
            }
        }
    }
}
