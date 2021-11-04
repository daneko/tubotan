package com.github.daneko.math

import java.math.BigDecimal
import java.math.BigInteger

operator fun BigInteger.times(a: Long): BigInteger =
    this * a.toBigInteger()

operator fun BigInteger.times(a: Int): BigInteger =
    this * a.toBigInteger()

operator fun BigInteger.div(a: Long): BigDecimal =
    this.toBigDecimal() / a.toBigDecimal()

operator fun BigInteger.div(a: Int): BigDecimal =
    this.toBigDecimal() / a.toBigDecimal()

infix operator fun Double.times(a: BigInteger): BigDecimal =
    this.toBigDecimal() * a.toBigDecimal()
