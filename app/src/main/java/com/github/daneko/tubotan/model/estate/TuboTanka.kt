package com.github.daneko.tubotan.model.estate

import com.github.daneko.android.text.Text
import com.github.daneko.math.div
import com.github.daneko.tubotan.R
import java.math.BigDecimal

data class TuboTanka(
    val price: EstatePrice,
    val occupiedArea: OccupiedArea,
) {

    val tanka = price / occupiedArea

    /**
     * xxx.yy 万円/坪
     */
    fun formatted(): Text {
        val str = "%.2f".format(tanka / 10_000)
        return Text("$str ") + Text(R.string.label_tubo_tanka_unit)
    }

    companion object {
        internal operator fun EstatePrice.div(area: OccupiedArea): BigDecimal {
            return this.value.toBigDecimal() / area.getTuboValue()
        }
    }
}
