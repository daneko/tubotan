package com.github.daneko.tubotan.model.estate

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.rightIfNotNull
import com.github.daneko.android.text.Text
import timber.log.Timber
import java.math.BigDecimal
import java.math.RoundingMode

data class OccupiedArea internal constructor(
    private val m2: BigDecimal,
    private val tubo: BigDecimal,
) {
    init {
        require(m2 > BigDecimal.ZERO)
        require(tubo > BigDecimal.ZERO)
        Timber.d("check $this")
    }

    fun getM2Value(): BigDecimal {
        return m2
    }

    fun formattedM2(): Text {
        return Text("%.2f m2".format(m2))
    }

    fun getTuboValue(): BigDecimal {
        return tubo
    }

    fun formattedTubo(): Text {
        return Text("%.2f 坪".format(tubo))
    }

    companion object {
        // ref : https://www.id-home.net/qa/%E5%B9%B3%E7%B1%B3%EF%BC%88%EF%BC%89%E3%81%8B%E3%82%89%E5%9D%AA%E3%81%B8%E6%8F%9B%E7%AE%97%E3%81%99%E3%82%8B%E8%A8%88%E7%AE%97%E6%96%B9%E6%B3%95%E3%82%92%E6%95%99%E3%81%88%E3%81%A6%E3%81%8F%E3%81%A0/
        private val coefficient = 0.3025.toBigDecimal().setScale(8, RoundingMode.HALF_UP)
        suspend fun createBy(meter2: String): Either<String, OccupiedArea> = either {
            val m2 = meter2.toBigDecimalOrNull().rightIfNotNull { "数値を入力してください" }.bind()
            runCatching { createByM2(m2) }.bind { "専有/延床面積を入力してください" }
        }

        internal fun createByM2(m2: BigDecimal) = run {
            val tmp = m2.setScale(8, RoundingMode.HALF_UP)
            OccupiedArea(
                m2 = tmp,
                tubo = tmp * coefficient,
            )
        }

        internal fun createByTubo(tubo: BigDecimal) = run {
            val tmp = tubo.setScale(8, RoundingMode.HALF_UP)
            OccupiedArea(
                m2 = tmp / coefficient,
                tubo = tmp,
            )
        }
    }
}
