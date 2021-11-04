package com.github.daneko.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * Stateful
 * https://developers.google.com/admob/android/banner#banner_sizes
 */
@Preview(heightDp = 50)
@Composable
fun AdmobAdBanner(
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111",
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "ad banner dummy preview",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    } else {
        val displayMetrics = displayMetrics()
        val width = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { ctx ->
                    AdView(ctx).apply {
                        this.adUnitId = adUnitId
                        adSize =
                            AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, width)
                        loadAd(AdRequest.Builder().build())
                    }
                },
            )
        }
    }
}
