package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.Either
import arrow.core.computations.either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.rightIfNotNull
import com.github.daneko.tubotan.R
import com.github.daneko.tubotan.model.estate.EstatePrice
import com.github.daneko.tubotan.model.estate.OccupiedArea
import kotlinx.coroutines.launch


@Preview
@Composable
fun TuboTanka() {
    var estatePrice: Either<String, EstatePrice> by remember { mutableStateOf("".left()) }
    var occupiedArea: Either<String, OccupiedArea> by remember { mutableStateOf("".left()) }
    val tuboTankaResult: Either<String, String> by remember(estatePrice, occupiedArea) {
        derivedStateOf {
            estatePrice.flatMap { price ->
                occupiedArea.map { area ->
                    val tuboTanka = (price.value.toDouble() / area.getTuboValue()) / 10000.0
                    "%.2f".format(tuboTanka)
                }
            }
        }
    }

    Column {
        EstatePriceInput {
            estatePrice = it
        }

        Spacer(modifier = Modifier.height(24.dp))

        OccupiedAreaInput {
            occupiedArea = it
        }

        when (val res = tuboTankaResult) {
            is Either.Left -> {
            }
            is Either.Right -> {
                Text(
                    text = res.value
                )
            }
        }
    }
}

@Preview
@Composable
fun EstatePriceInput(
    onResult: (Either<String, EstatePrice>) -> Unit = {},
) {
    var value by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
    var errorMsg by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column {
        TextField(
            value = value,
            onValueChange = { newValue ->
                value = newValue
                scope.launch {
                    either<String, EstatePrice> {
                        val price = newValue.toIntOrNull().rightIfNotNull { "数字を入力してください" }.bind()
                        runCatching { EstatePrice.createBy(price) }.bind { "0以上の20000以下で入力してください" }
                    }.apply(onResult).run {
                        if (this is Either.Left) {
                            errorMsg = this.value
                        }
                        showError = this is Either.Left
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(stringResource(R.string.label_estate_price))
            },
            placeholder = {
                Text(
                    text = "4000",
                )
            },
            trailingIcon = {
                Text(stringResource(R.string.label_price_unit))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            singleLine = true,
            maxLines = 1,
        )

        if (showError) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colors.error.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}

@Preview
@Composable
fun OccupiedAreaInput(
    onResult: (Either<String, OccupiedArea>) -> Unit = {},
) {

    var value by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
    var errorMsg by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column {
        TextField(
            value = value,
            onValueChange = { newValue ->
                value = newValue
                scope.launch {
                    either<String, OccupiedArea> {
                        val m2 = newValue.toDoubleOrNull().rightIfNotNull { "数値を入力してください" }.bind()
                        runCatching { OccupiedArea.createByM2(m2) }.bind { "専有/延床面積を入力してください" }
                    }.apply(onResult).run {
                        if (this is Either.Left) {
                            errorMsg = this.value
                        }
                        showError = this is Either.Left
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(stringResource(R.string.label_occupied_area))
            },
            placeholder = {
                Text(
                    text = "67.24",
                )
            },
            trailingIcon = {
                Text(stringResource(R.string.label_occupied_area_unit_m2))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            singleLine = true,
            maxLines = 1,
        )

        if (showError) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colors.error.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}