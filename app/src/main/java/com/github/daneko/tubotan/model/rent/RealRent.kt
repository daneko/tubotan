package com.github.daneko.tubotan.model.rent

/**
 * @param baseRent 月家賃
 * @param managementFee 月管理費等
 * @param reikin 礼金
 * @param oneshotFee 契約時に掛かるその他金額
 * @param otherEachMonthFee 毎月掛かるその他金額
 */
data class RealRent(
    val baseRent: BaseRent,
    val managementFee: ManagementFee,
    val reikin: Reikin,
    val oneshotFee: Long = 0,
    val otherEachMonthFee: Long = 0,
) {
    init {
        require(oneshotFee >= 0)
        require(otherEachMonthFee >= 0)
    }

    // TODO 計算結果を考えるとやっぱりBigDecimalじゃないと？？ (テストケース確認)
    fun calcRealRent(byYear: Long): Long {
        require(byYear > 0)
        val monthCount = byYear * 12
        val monthFee = baseRent.price +
            managementFee.price +
            otherEachMonthFee
        val oneshotTotalFee = reikin.price + oneshotFee
        return if (oneshotTotalFee == 0L) {
            monthFee
        } else {
            (monthFee * monthCount + oneshotTotalFee) / monthCount
        }
    }
}

@JvmInline
value class BaseRent(val price: Long) {
    init {
        require(price > 0)
    }
}

@JvmInline
value class ManagementFee(val price: Long) {
    init {
        require(price >= 0)
    }
}

@JvmInline
value class Reikin(val price: Long) {
    init {
        require(price >= 0)
    }

    companion object {
        val Zero = Reikin(0)
        fun createByMonthCount(monthCount: Double, baseRent: BaseRent): Reikin {
            require(monthCount >= 0)
            return Reikin((monthCount * baseRent.price).toLong())
        }
    }
}
