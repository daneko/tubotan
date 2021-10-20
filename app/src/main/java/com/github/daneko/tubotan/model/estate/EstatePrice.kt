package com.github.daneko.tubotan.model.estate

@JvmInline
value class EstatePrice internal constructor(
    val value: Int,
) {
    init {
        require(value >= 0)
    }

    companion object {
        fun createBy(unit10_000: Int) =
            EstatePrice(unit10_000 * 10000)
    }
}
