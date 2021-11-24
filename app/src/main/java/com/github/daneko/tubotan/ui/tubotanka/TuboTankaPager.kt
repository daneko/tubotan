package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.daneko.tubotan.ui.components.TabContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TuboTankaPager() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    TabContent(
        contents = listOf("坪単価計算", "単価から計算"),
        contentToTitle = { it },
        pagerState = pagerState,
        onClickTab = { pageIndex ->
            // Animate to the selected page when clicked
            scope.launch {
                pagerState.animateScrollToPage(pageIndex)
            }
        }
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (page) {
                0 -> {
                    TuboTanka()
                }
                1 -> {
                    TuboTankaInverse()
                }
            }
        }
    }
}
