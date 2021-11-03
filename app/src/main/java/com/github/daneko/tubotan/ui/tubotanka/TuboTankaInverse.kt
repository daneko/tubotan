package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.Either
import arrow.core.computations.either
import arrow.core.computations.nullable
import arrow.core.left
import com.github.daneko.math.times
import com.github.daneko.tubotan.R
import com.github.daneko.tubotan.model.estate.OccupiedArea
import timber.log.Timber

@Preview
@Composable
fun TuboTankaInverse() {

    var tuboTankaInput by remember { mutableStateOf(TextFieldValue()) }
    var tuboTanka: Either<String, Int>? by remember { mutableStateOf(null) }
    var occupiedArea: Either<String, OccupiedArea> by remember { mutableStateOf("".left()) }

    val result by produceState<Either<String, String>?>(
        initialValue = null,
        key1 = tuboTanka,
        key2 = occupiedArea,
    ) {
        value = nullable {
            either {
                val tanka = tuboTanka.bind().bind()
                val area = occupiedArea.bind()
                Timber.d("tanka : $tanka, area : $area")
                (area.getTuboValue() * tanka.toDouble()).toInt().toString()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        TuboTankaInput(
            value = tuboTankaInput,
            onValueChange = {
                tuboTankaInput = it
            },
            either = tuboTanka,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TODO
        OccupiedAreaInput(
            value = TextFieldValue(),
            onValueChange = {},
            either = null,
        )
        Spacer(modifier = Modifier.height(24.dp))
        result?.fold(
            ifLeft = {},
            ifRight = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$it ${stringResource(id = R.string.label_price_unit)}",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
}

