package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * isError: Either.Leftの場合true
 */
@Composable
fun <A, B> TextFieldWithError(
    modifier: Modifier = Modifier,
    initValue: TextFieldValue = TextFieldValue(),
    onValueChange: suspend CoroutineScope.(TextFieldValue) -> Either<A, B>,
    errorValue: (A) -> AnnotatedString,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape =
        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {

    var valueInternal by remember { mutableStateOf(initValue) }
    var isError by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf(AnnotatedString("")) }
    val scope = rememberCoroutineScope()
    Column {
        TextField(
            value = valueInternal,
            onValueChange = { newValue ->
                valueInternal = newValue
                scope.launch {
                    onValueChange(newValue).run {
                        if (this is Either.Left) {
                            errorMsg = errorValue(this.value)
                        }
                        isError = this is Either.Left
                    }
                }
            },
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
        )

        if (isError) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colors.error.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}
