package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.Either
import arrow.core.computations.either
import arrow.core.left
import arrow.core.rightIfNotNull
import com.github.daneko.tubotan.R
import com.github.daneko.tubotan.model.estate.OccupiedArea
import timber.log.Timber

@Preview
@Composable
fun TuboTankaInverse() {

    var tuboTanka: Either<String, Int> by remember { mutableStateOf("".left()) }
    var occupiedArea: Either<String, OccupiedArea> by remember { mutableStateOf("".left()) }

    val result by produceState<Either<String, String>>(
        initialValue = "".left(),
        key1 = tuboTanka,
        key2 = occupiedArea,
    ) {
        value = either {
            val tanka = tuboTanka.bind()
            val area = occupiedArea.bind()
            Timber.d("tanka : $tanka, area : $area")
            (area.getTuboValue() * tanka.toDouble()).toInt().toString()
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        TuboTankaInput {
            tuboTanka = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        OccupiedAreaInput {
            occupiedArea = it
        }
        Spacer(modifier = Modifier.height(24.dp))
        result.fold(
            ifLeft = {},
            ifRight = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$it ${stringResource(id = R.string.label_price_unit)}",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
}

@Preview
@Composable
fun TuboTankaInput(
    onResult: (Either<String, Int>) -> Unit = {},
) {
    TextFieldWithError(
        onValueChange = {
            it.text.toIntOrNull().rightIfNotNull { "坪単価を入力してください" }.apply(onResult)
        },
        errorValue = { AnnotatedString(it) },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(stringResource(R.string.label_tubo_tanka))
        },
        placeholder = {
            Text(
                text = "40",
            )
        },
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
