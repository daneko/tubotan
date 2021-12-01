package com.github.daneko.tubotan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RootContentScreen() {
    val scrollBehavior = remember { TopAppBarDefaults.enterAlwaysScrollBehavior() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        drawerGesturesEnabled = false,
        topBar = {
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                navigationIcon = {},
                actions = {},
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            Column {
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    listOf("label" to Icons.Filled.Favorite).forEachIndexed { index, (label, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, null) },
                            label = { Text("$index : $label") },
                            onClick = {},
                            selected = false,
                            alwaysShowLabel = false,
                        )
                    }
                }
                AdmobAdBanner()
            }
        },
        content = {
            TuboTankaPager()
        },
    )
}
