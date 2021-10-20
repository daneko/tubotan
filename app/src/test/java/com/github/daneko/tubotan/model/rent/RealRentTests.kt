package com.github.daneko.tubotan.model.rent

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RealRentTests : FunSpec({
    test("calc real rent fee 1") {
        val target = RealRent(
            baseRent = BaseRent(100_000L),
            managementFee = ManagementFee(0L),
            reikin = Reikin.Zero,
            oneshotFee = 0L,
            otherEachMonthFee = 0L,
        )
        target.calcRealRent(1).shouldBe(target.baseRent.price)
    }

    test("calc real rent fee 2") {
        val baseRent = BaseRent(100_000L)
        val target = RealRent(
            baseRent = baseRent,
            managementFee = ManagementFee(10_000L),
            reikin = Reikin.createByMonthCount(2.0, baseRent),
            oneshotFee = 0L,
            otherEachMonthFee = 500L,
        )
        target.calcRealRent(1).shouldBe(127_167L)
        target.calcRealRent(2).shouldBe(118_833L)
    }
})
