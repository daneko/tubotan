package com.github.daneko.tubotan.model.estate

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.setDefaultScale() = this.setScale(8, RoundingMode.HALF_UP)!!
