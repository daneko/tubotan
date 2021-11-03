package com.github.daneko.math

import java.math.BigInteger

operator fun BigInteger.times(a: Long): BigInteger =
    this * a.toBigInteger()

operator fun BigInteger.times(a: Int): BigInteger =
    this * a.toBigInteger()

operator fun BigInteger.div(a: Long): BigInteger =
    this / a.toBigInteger()

operator fun BigInteger.div(a: Int): BigInteger =
    this / a.toBigInteger()
