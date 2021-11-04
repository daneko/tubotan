package com.github.daneko.tubotan.model.estate

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.rightIfNotNull
import com.github.daneko.android.text.Text
import com.github.daneko.math.div
import com.github.daneko.math.times
import com.github.daneko.tubotan.R
import java.math.BigInteger

/**
 * @property value yen
 */
@JvmInline
value class EstatePrice internal constructor(
    val value: BigInteger,
) {
    init {
        require(value >= BigInteger.ZERO)
    }

    fun formatted(): Text {
        val tanka = value.toBigDecimal().setDefaultScale() / 10_000L
        return Text("%.2f ".format(tanka)) + Text(R.string.label_price_unit)
    }

    companion object {
        fun createBy(
            tuboTanka: TuboTanka,
            occupiedArea: OccupiedArea,
        ): EstatePrice {
            val price = (tuboTanka.tanka * occupiedArea.getTuboValue()).toBigInteger()
            return EstatePrice(price)
        }

        suspend fun createBy(yen10_000: String): Either<String, EstatePrice> =
            either {
                val price = yen10_000.toBigIntegerOrNull().rightIfNotNull { "数字を入力してください" }.bind()
                runCatching { EstatePrice(price * 10_000) }.bind { "0以上の20000以下で入力してください" }
            }
    }
}
