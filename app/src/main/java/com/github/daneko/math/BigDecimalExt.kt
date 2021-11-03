package com.github.daneko.math

import java.math.BigDecimal

operator fun BigDecimal.times(a: Long): BigDecimal =
    this * a.toBigDecimal()

operator fun BigDecimal.times(a: Int): BigDecimal =
    this * a.toBigDecimal()

operator fun BigDecimal.times(a: Float): BigDecimal =
    this * a.toBigDecimal()

operator fun BigDecimal.times(a: Double): BigDecimal =
    this * a.toBigDecimal()

operator fun BigDecimal.div(a: Long): BigDecimal =
    this / a.toBigDecimal()

operator fun BigDecimal.div(a: Int): BigDecimal =
    this / a.toBigDecimal()

operator fun BigDecimal.div(a: Float): BigDecimal =
    this / a.toBigDecimal()

operator fun BigDecimal.div(a: Double): BigDecimal =
    this / a.toBigDecimal()
