package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import arrow.core.Either
import arrow.core.computations.either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.rightIfNotNull
import kotlinx.coroutines.launch

@Preview
@Composable
fun Sample() {

    var formAInput: String by remember { mutableStateOf("") }
    var formBInput: String by remember { mutableStateOf("") }
    var formAResult: Either<String, Int> by remember { mutableStateOf("".left()) }
    var formBResult: Either<String, Int> by remember { mutableStateOf("".left()) }
    val result by produceState<Either<String, String>>(
        initialValue = "".left(),
        key1 = formAResult,
        key2 = formBResult,
        producer = {
            value = either {
                val a = formAResult.bind()
                val b = formBResult.bind()
                "a + b = ${a + b}"
            }
        }
    )

    val result2 by remember(key1 = formAResult, key2 = formBResult) {
        derivedStateOf {
            formAResult.flatMap { a ->
                formBResult.map { b ->
                    "a + b = ${a + b}"
                }
            }
        }
    }

    val scope = rememberCoroutineScope()

    Column {
        TextField(
            value = formAInput,
            onValueChange = {
                formAInput = it
                formAResult =
                    it.toIntOrNull().rightIfNotNull { "form A 数値を入力" }
            }
        )

        TextField(
            value = formBInput,
            onValueChange = {
                formBInput = it
                scope.launch {
                    formBResult = either {
                        it.toIntOrNull().rightIfNotNull { "form B 数値を入力" }.bind()
                    }
                }
            }
        )

        result.fold(
            ifLeft = {
                Text("result error : $it")
            },
            ifRight = {
                Text(text = "result : $it")
            }
        )

        result2.fold(
            ifLeft = {
                Text("result2 error : $it")
            },
            ifRight = {
                Text(text = "result2 : $it")
            }
        )
    }
}
