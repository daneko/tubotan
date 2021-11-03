package com.github.daneko.tubotan.ui.tubotanka

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TuboTankaPager() {
    val scope = rememberCoroutineScope()
    val pageTitles = remember { listOf("坪単価計算", "単価から計算") }
    val pagerState = rememberPagerState()

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            // Add tabs for all of our pages
            pageTitles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        // Animate to the selected page when clicked
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.weight(1f),
            count = pageTitles.size,
            state = pagerState,
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
}
