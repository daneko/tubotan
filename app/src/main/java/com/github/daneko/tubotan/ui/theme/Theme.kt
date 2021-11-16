package com.github.daneko.tubotan.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.MaterialTheme as OldMaterialTheme

@Composable
fun TubotanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamic: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        if (dynamic) {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (darkTheme) {
                darkColorScheme()
            } else {
                lightColorScheme()
            }
        }

    val colors = colorScheme.toColors()

    OldMaterialTheme(
        colors = colors,
        typography = Typography.toOldTypography(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}

@Composable
fun ColorScheme.toColors(darkTheme: Boolean = isSystemInDarkTheme()): Colors =
    Colors(
        primary = primary,
        primaryVariant = primaryContainer,
        secondary = secondary,
        secondaryVariant = secondaryContainer,
        background = background,
        surface = surface,
        error = error,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        onError = onError,
        isLight = !darkTheme,
    )

@Composable
fun Typography.toOldTypography() = androidx.compose.material.Typography(
    h1 = displayLarge,
    h2 = displayMedium,
    h3 = displaySmall,
    h4 = headlineLarge,
    h5 = headlineMedium,
    h6 = headlineSmall,
    subtitle1 = titleLarge,
    subtitle2 = titleMedium,
    body1 = bodyLarge,
    body2 = bodyMedium,
    button = labelLarge,
    caption = labelMedium,
    overline = labelSmall,
)
