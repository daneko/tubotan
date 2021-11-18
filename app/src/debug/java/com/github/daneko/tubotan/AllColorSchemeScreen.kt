package com.github.daneko.tubotan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    showBackground = true,
    backgroundColor = android.graphics.Color.WHITE.toLong(),
)
@Composable
fun AllColorSchemeScreen() {
    val schemeList = listOf(
        MaterialTheme.colorScheme.primary to "primary",
        MaterialTheme.colorScheme.onPrimary to "onPrimary",
        MaterialTheme.colorScheme.primaryContainer to "primaryContainer",
        MaterialTheme.colorScheme.onPrimaryContainer to "onPrimaryContainer",
        MaterialTheme.colorScheme.inversePrimary to "inversePrimary",
        MaterialTheme.colorScheme.secondary to "secondary",
        MaterialTheme.colorScheme.onSecondary to "onSecondary",
        MaterialTheme.colorScheme.secondaryContainer to "secondaryContainer",
        MaterialTheme.colorScheme.onSecondaryContainer to "onSecondaryContainer",
        MaterialTheme.colorScheme.tertiary to "tertiary",
        MaterialTheme.colorScheme.onTertiary to "onTertiary",
        MaterialTheme.colorScheme.tertiaryContainer to "tertiaryContainer",
        MaterialTheme.colorScheme.onTertiaryContainer to "onTertiaryContainer",
        MaterialTheme.colorScheme.background to "background",
        MaterialTheme.colorScheme.onBackground to "onBackground",
        MaterialTheme.colorScheme.surface to "surface",
        MaterialTheme.colorScheme.onSurface to "onSurface",
        MaterialTheme.colorScheme.surfaceVariant to "surfaceVariant",
        MaterialTheme.colorScheme.onSurfaceVariant to "onSurfaceVariant",
        MaterialTheme.colorScheme.inverseSurface to "inverseSurface",
        MaterialTheme.colorScheme.inverseOnSurface to "inverseOnSurface",
        MaterialTheme.colorScheme.error to "error",
        MaterialTheme.colorScheme.onError to "onError",
        MaterialTheme.colorScheme.errorContainer to "errorContainer",
        MaterialTheme.colorScheme.onErrorContainer to "onErrorContainer",
        MaterialTheme.colorScheme.outline to "outline",
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {
        schemeList.forEachIndexed { index, (color, name) ->
            if (index > 0) {
                Divider()
            }
            ShowColorComponent(color, name)
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = android.graphics.Color.WHITE.toLong(),
)
@Composable
fun ShowColorComponent(
    color: Color = Color.Green,
    name: String = "preview",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center,

        ) {
            Text(text = name)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f)
                .background(color = color)
        )
    }
}
