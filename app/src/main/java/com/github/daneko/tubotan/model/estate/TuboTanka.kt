package com.github.daneko.tubotan.model.estate

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.rightIfNotNull
import com.github.daneko.android.text.Text
import com.github.daneko.math.div
import com.github.daneko.math.times
import com.github.daneko.tubotan.R
import java.math.BigDecimal

/**
 * @property tanka : yen
 */
@JvmInline
value class TuboTanka internal constructor(
    val tanka: BigDecimal,
) {
    /**
     * xxx.yy 万円/坪
     */
    fun formatted(): Text {
        val str = "%.2f".format(tanka / 10_000)
        return Text("$str ") + Text(R.string.label_tubo_tanka_unit)
    }

    companion object {
        internal operator fun EstatePrice.div(area: OccupiedArea): BigDecimal {
            return this.value.setDefaultScale() / area.getTuboValue()
        }

        fun createBy(
            price: EstatePrice,
            occupiedArea: OccupiedArea,
        ): TuboTanka {
            return TuboTanka(price / occupiedArea)
        }

        /**
         * @param yen10_000 : e.g 300.0, 300
         */
        suspend fun createBy(
            yen10_000: String,
        ): Either<String, TuboTanka> =
            either {
                val price = yen10_000.toBigDecimalOrNull().rightIfNotNull { "数字を入力してください" }.bind()
                TuboTanka((price * 10_000L).setDefaultScale())
            }
    }
}
