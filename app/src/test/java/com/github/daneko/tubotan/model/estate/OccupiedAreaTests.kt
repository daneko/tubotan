package com.github.daneko.tubotan.model.estate

import androidx.test.core.app.ApplicationProvider
import com.github.daneko.tubotan.MockApplication
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.robolectric.RobolectricTest
import io.kotest.matchers.shouldBe

/**
 * 四捨五入関連あるので、下の通りではないが、参考までに
 * https://www.id-home.net/knowledge/%e4%b8%80%e5%9d%aa%e3%81%af%e4%bd%95%e5%b9%b3%e7%b1%b3%ef%bc%9f%e4%bd%95%e7%95%b3%ef%bc%9f%e9%9d%a2%e7%a9%8d%e6%8f%9b%e7%ae%97%e4%b8%80%e8%a6%a7%e8%a1%a8/
 */
@RobolectricTest
class OccupiedAreaTests : FunSpec({
    context("check OccupiedArea#formatted method") {
        val mockContext = ApplicationProvider.getApplicationContext<MockApplication>()
        listOf(
            1 to 3.31,
            5 to 16.53,
            10 to 33.06,
            15 to 49.59,
            20 to 66.12,
            25 to 82.64,
            30 to 99.17,
            35 to 115.70,
            40 to 132.23,
            45 to 148.76,
            50 to 165.29,
            55 to 181.82,
            60 to 198.35,
            65 to 214.88,
            70 to 231.40,
            75 to 247.93,
            80 to 264.46,
            85 to 280.99,
            90 to 297.52,
            95 to 314.05,
            100 to 330.58,
        ).forEach { (tubo, m2) ->
            test("case ${tubo}坪 to ${m2}m2") {
                OccupiedArea.createByTubo(tubo.toBigDecimal()).formattedM2()
                    .get(context = mockContext) shouldBe "%.2f m2".format(m2)
            }

            test("case ${m2}m2 to ${tubo}坪") {
                OccupiedArea.createByM2(m2.toBigDecimal()).formattedTubo()
                    .get(context = mockContext) shouldBe "%.2f 坪".format(tubo.toBigDecimal())
            }
        }
    }
})
