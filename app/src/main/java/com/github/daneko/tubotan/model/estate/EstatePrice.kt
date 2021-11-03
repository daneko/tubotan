package com.github.daneko.tubotan.model.estate

import arrow.core.Either
import arrow.core.computations.either
import arrow.core.rightIfNotNull
import com.github.daneko.math.times
import java.math.BigInteger

@JvmInline
value class EstatePrice internal constructor(
    val value: BigInteger,
) {
    init {
        require(value >= BigInteger.ZERO)
    }

    companion object {
        suspend fun createBy(yen10_000: String): Either<String, EstatePrice> =
            either {
                val price = yen10_000.toBigIntegerOrNull().rightIfNotNull { "数字を入力してください" }.bind()
                runCatching { EstatePrice(price * 10_000) }.bind { "0以上の20000以下で入力してください" }
            }
    }
}
