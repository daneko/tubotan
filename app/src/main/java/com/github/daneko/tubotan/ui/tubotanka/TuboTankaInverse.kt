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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.Either
import arrow.core.computations.either
import arrow.core.computations.nullable
import com.github.daneko.tubotan.model.estate.EstatePrice
import com.github.daneko.tubotan.model.estate.OccupiedArea
import com.github.daneko.tubotan.model.estate.TuboTanka
import timber.log.Timber

@Preview(
    showBackground = true,
    backgroundColor = android.graphics.Color.WHITE.toLong(),
)
@Composable
fun TuboTankaInverse() {

    val context = LocalContext.current

    var tuboTankaInput by remember { mutableStateOf(TextFieldValue()) }
    var occupiedAreaInput by remember { mutableStateOf(TextFieldValue()) }
    val tuboTanka: Either<String, TuboTanka>? by rememberTuboTankaEither(tanka = tuboTankaInput.text)
    val occupiedArea: Either<String, OccupiedArea>? by rememberOccupiedAreaEither(meter2 = occupiedAreaInput.text)

    val result by produceState<Either<String, EstatePrice>?>(
        initialValue = null,
        key1 = tuboTanka,
        key2 = occupiedArea,
    ) {
        value = nullable {
            either {
                val tanka = tuboTanka.bind().bind()
                val area = occupiedArea.bind().bind()
                Timber.d("tanka : $tanka, area : $area")
                EstatePrice.createBy(tanka, area)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        TuboTankaInput(
            value = tuboTankaInput,
            onValueChange = {
                tuboTankaInput = it
            },
            either = tuboTanka,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OccupiedAreaInput(
            value = occupiedAreaInput,
            onValueChange = { occupiedAreaInput = it },
            either = occupiedArea,
        )
        Spacer(modifier = Modifier.height(24.dp))
        result?.fold(
            ifLeft = {},
            ifRight = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.formatted().get(context),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
            },
        )
    }
}
