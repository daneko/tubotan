package com.github.daneko.tubotan.model.estate

class OccupiedArea internal constructor(
    private val m2: Double,
    private val tubo: Double,
) {
    init {
        require(m2 > 0)
        require(tubo > 0)
    }

    fun getM2Value(): Double {
        return m2
    }

    fun getTuboValue(): Double {
        return tubo
    }

    companion object {
        fun createByM2(m2: Double) = OccupiedArea(
            m2 = m2,
            tubo = m2 / 3.3,
        )

        fun createByTubo(tubo: Double) = OccupiedArea(
            m2 = tubo * 3.3,
            tubo = tubo,
        )
    }
}
