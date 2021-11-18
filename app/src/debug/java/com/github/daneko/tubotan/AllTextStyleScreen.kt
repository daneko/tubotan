package com.github.daneko.tubotan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    showBackground = true,
    backgroundColor = android.graphics.Color.WHITE.toLong(),
)
@Composable
fun AllTextStyleScreen() {
    val scrollState = rememberScrollState()
    val oldTextStyles = MaterialTheme.typography.run {
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
    val newTextStyles = androidx.compose.material3.MaterialTheme.typography.run {
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
            .verticalScroll(
                state = scrollState,
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        oldTextStyles.zip(newTextStyles).forEachIndexed { index, (old, new) ->
            if (index > 0) {
                Divider(
                    thickness = 2.dp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(8.dp)
                ) {
                    ShowTextStyle(old.first, old.second)
                }
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(8.dp)
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
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // header
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = name,
            style = src,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = name,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Column(
                modifier = Modifier.weight(0.3f)
            ) {
                listOf("size", "weight", "line height", "letter spacing").forEach {
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = it,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                listOf(
                    src.fontSize,
                    src.fontWeight?.weight,
                    src.lineHeight,
                    src.letterSpacing,
                ).forEach {
                    Text(text = " : $it")
                }
            }
        }
    }
}
