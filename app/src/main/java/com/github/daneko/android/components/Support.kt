package com.github.daneko.android.components

import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

/**
 * copy from [androidx.compose.ui.res.resources()]
 */
@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
@ReadOnlyComposable
fun displayMetrics(): DisplayMetrics = resources().displayMetrics
