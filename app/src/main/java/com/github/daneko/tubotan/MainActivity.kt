package com.github.daneko.tubotan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.daneko.android.components.AdmobAdBanner
import com.github.daneko.tubotan.ui.theme.TubotanTheme
import com.github.daneko.tubotan.ui.tubotanka.TuboTankaPager
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        setContent {
            TubotanTheme {
                ProvideWindowInsets {
                    RootContentScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RootContentScreen() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
            ){
               TuboTankaPager()
            }
            AdmobAdBanner()
        }
    }
}
