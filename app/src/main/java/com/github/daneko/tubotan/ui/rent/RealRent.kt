package com.github.daneko.tubotan.ui.rent

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * 家賃・管理費等・礼金等を指定年数で割って、実質家賃を割り出す
 */
@Composable
fun RealRent() {

    Column {
        // 家賃
        TextField(
            value = "hoge",
            onValueChange = {},
        )

        // 管理費等
        TextField(
            value = "hoge",
            onValueChange = {},
        )

        // 礼金
        TextField(
            value = "hoge",
            onValueChange = {},
        )

        // 仲介手数料等
        TextField(
            value = "hoge",
            onValueChange = {},
        )

        // その他月にかかるもの
        TextField(
            value = "",
            onValueChange = {},
        )

        // その他一発かかるもの
        TextField(
            value = "",
            onValueChange = {},
        )

        // 結果1年間の場合
        Text(
            text = "",
        )
        // 2年間の場合
        Text(
            text = "",
        )
    }
}
