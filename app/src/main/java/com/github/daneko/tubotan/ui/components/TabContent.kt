package com.github.daneko.tubotan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState

/**
 * ScrollableTabなどがMaterial3対応するまでの暫定対応
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun <A> TabContent(
    contents: List<A>,
    contentToTitle: (A) -> String,
    pagerState: PagerState,
    onClickTab: (pageIndex: Int) -> Unit,
    pageContent: @Composable PagerScope.(page: Int) -> Unit,
) {
    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        ) {
            contents.map(contentToTitle).forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = { onClickTab(index) },
                )
            }
        }
        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            count = contents.size,
            state = pagerState,
        ) { page ->
            pageContent(page)
        }
    }
}
