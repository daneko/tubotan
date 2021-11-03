package com.github.daneko.tubotan.model.estate

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.github.daneko.tubotan.MockApplication
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.extensions.robolectric.RobolectricTest
import io.kotest.matchers.shouldBe

/**
 * https://www.id-home.net/knowledge/%e4%b8%80%e5%9d%aa%e3%81%af%e4%bd%95%e5%b9%b3%e7%b1%b3%ef%bc%9f%e4%bd%95%e7%95%b3%ef%bc%9f%e9%9d%a2%e7%a9%8d%e6%8f%9b%e7%ae%97%e4%b8%80%e8%a6%a7%e8%a1%a8/
 */
@OptIn(ExperimentalKotest::class)
@RobolectricTest
class OccupiedAreaTests : FunSpec({
    withData(
        nameFn = { (a, b) -> "${a}坪 = ${b}m2" },
        ts = listOf(
            1 to 3.31,
            5 to 16.52,
            10 to 33.05,
            15 to 49.58,
            20 to 66.11,
            25 to 82.64,
            30 to 99.17,
            35 to 115.70,
            40 to 132.23,
            45 to 148.76,
            50 to 165.28,
            55 to 181.81,
            60 to 198.34,
            65 to 214.87,
            70 to 231.40,
            75 to 247.93,
            80 to 264.46,
            85 to 280.99,
            90 to 297.52,
            95 to 314.04,
            100 to 330.57,
        )
    ) { (tubo, m2) ->
        val context = ApplicationProvider.getApplicationContext<MockApplication>()
        OccupiedArea.createByTubo(tubo.toBigDecimal()).formattedM2().get(context = context) shouldBe "$m2 m2"
    }
})