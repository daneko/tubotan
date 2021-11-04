package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import arrow.core.Either
import com.github.daneko.tubotan.R
import com.github.daneko.tubotan.model.estate.EstatePrice
import com.github.daneko.tubotan.model.estate.OccupiedArea
import com.github.daneko.tubotan.model.estate.TuboTanka
import com.github.daneko.tubotan.ui.components.TextFieldWithEither

@Composable
fun rememberEstatePriceEither(yen10_000: String): State<Either<String, EstatePrice>?> =
    produceState<Either<String, EstatePrice>?>(
        initialValue = null,
        key1 = yen10_000,
        producer = {
            value = if (yen10_000.isBlank()) null else EstatePrice.createBy(yen10_000)
        },
    )

@Composable
fun EstatePriceInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    either: Either<String, EstatePrice>?,
) {
    TextFieldWithEither(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        either = either,
        getErrorValue = { AnnotatedString(it) },
        label = {
            Text(stringResource(R.string.label_estate_price))
        },
        placeholder = { Text(text = "4000") },
        trailingIcon = {
            Text(stringResource(R.string.label_price_unit))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun rememberOccupiedAreaEither(meter2: String): State<Either<String, OccupiedArea>?> =
    produceState<Either<String, OccupiedArea>?>(
        initialValue = null,
        key1 = meter2,
        producer = {
            value = if (meter2.isBlank()) null else OccupiedArea.createBy(meter2)
        },
    )

@Composable
fun OccupiedAreaInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    either: Either<String, OccupiedArea>?,
) {
    TextFieldWithEither(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        either = either,
        getErrorValue = { AnnotatedString(it) },
        label = {
            Text(stringResource(R.string.label_occupied_area))
        },
        placeholder = { Text(text = "67.24") },
        trailingIcon = {
            Text(stringResource(R.string.label_occupied_area_unit_m2))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun rememberTuboTankaEither(tanka: String): State<Either<String, TuboTanka>?> =
    produceState<Either<String, TuboTanka>?>(
        initialValue = null,
        key1 = tanka,
        producer = {
            value = if (tanka.isBlank()) null else TuboTanka.createBy(tanka)
        },
    )

@Composable
fun TuboTankaInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    either: Either<String, TuboTanka>?,
) {
    TextFieldWithEither(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        either = either,
        getErrorValue = { AnnotatedString(it) },
        label = {
            Text(stringResource(R.string.label_tubo_tanka))
        },
        placeholder = { Text(text = "40") },
        trailingIcon = {
            Text(stringResource(R.string.label_tubo_tanka_unit))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        ),
        singleLine = true,
        maxLines = 1,
    )
}
