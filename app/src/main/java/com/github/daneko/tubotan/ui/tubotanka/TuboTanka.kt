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
import com.github.daneko.android.text.Text
import com.github.daneko.tubotan.model.estate.TuboTanka as TuboTankaValue

@Preview
@Composable
fun TuboTanka() {

    val context = LocalContext.current

    var estatePriceInput by remember { mutableStateOf(TextFieldValue()) }
    val estatePriceState by rememberEstatePriceEither(yen10_000 = estatePriceInput.text)

    var occupiedAreaInput by remember { mutableStateOf(TextFieldValue()) }
    val occupiedAreaState by rememberOccupiedAreaEither(meter2 = occupiedAreaInput.text)

    val tuboTankaResult by produceState<Either<String, Text>?>(
        initialValue = null,
        key1 = estatePriceState,
        key2 = occupiedAreaState,
    ) {
        value = nullable {
            val ep = estatePriceState.bind()
            val oa = occupiedAreaState.bind()
            either {
                val price = ep.bind()
                val area = oa.bind()
                TuboTankaValue(price, area).formatted()
            }
        }
    }

    Column {

        EstatePriceInput(
            value = estatePriceInput,
            onValueChange = { estatePriceInput = it },
            either = estatePriceState,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OccupiedAreaInput(
            value = occupiedAreaInput,
            onValueChange = { occupiedAreaInput = it },
            either = occupiedAreaState,
        )

        Spacer(modifier = Modifier.height(24.dp))

        tuboTankaResult?.fold(
            ifLeft = {},
            ifRight = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.get(context),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}
