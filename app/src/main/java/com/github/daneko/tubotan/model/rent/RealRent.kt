package com.github.daneko.tubotan.model.rent

import android.os.Parcelable
import com.github.daneko.math.div
import com.github.daneko.math.times
import kotlinx.parcelize.Parcelize
import java.math.BigInteger

/**
 * @param baseRent 月家賃
 * @param managementFee 月管理費等
 * @param reikin 礼金
 * @param oneshotFee 契約時に掛かるその他金額
 * @param otherEachMonthFee 毎月掛かるその他金額
 */
@Parcelize
data class RealRent(
    val baseRent: BaseRent,
    val managementFee: ManagementFee,
    val reikin: Reikin,
    val oneshotFee: BigInteger = BigInteger.ZERO,
    val otherEachMonthFee: BigInteger = BigInteger.ZERO,
) : Parcelable {
    init {
        require(oneshotFee >= BigInteger.ZERO)
        require(otherEachMonthFee >= BigInteger.ZERO)
    }

    fun calcRealRent(byYear: Long): BigInteger {
        require(byYear > 0)
        val monthCount = byYear * 12
        val monthFee = baseRent.price +
            managementFee.price +
            otherEachMonthFee
        val oneshotTotalFee = reikin.price + oneshotFee
        return if (oneshotTotalFee == BigInteger.ZERO) {
            monthFee
        } else {
            ((monthFee * monthCount + oneshotTotalFee) / monthCount).toBigInteger()
        }
    }
}

@JvmInline
@Parcelize
value class BaseRent(val price: BigInteger) : Parcelable {
    constructor(p: Long) : this(p.toBigInteger())

    init {
        require(price > BigInteger.ZERO)
    }
}

@JvmInline
@Parcelize
value class ManagementFee(val price: BigInteger) : Parcelable {
    constructor(p: Long) : this(p.toBigInteger())
    init {
        require(price >= BigInteger.ZERO)
    }
}

@JvmInline
@Parcelize
value class Reikin(val price: BigInteger) : Parcelable {
    init {
        require(price >= BigInteger.ZERO)
    }

    companion object {
        val Zero = Reikin(BigInteger.ZERO)
        fun createByMonthCount(monthCount: Double, baseRent: BaseRent): Reikin {
            require(monthCount >= 0)
            return Reikin((monthCount * baseRent.price).toBigInteger())
        }
    }
}
