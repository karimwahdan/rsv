@file:OptIn(ExperimentalFoundationApi::class)

package com.karimwahdan.rsv.carousels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) +
                    pagerState.currentPageOffsetFraction)
                .absoluteValue

        val transformation =lerp(start = 0.7f,stop = 1f,fraction = 1f - pageOffset.coerceIn(0f, 1f))
        alpha = transformation
        scaleY = transformation
    }

@Composable
fun DotIndicators(
    pageCount: Int, pagerState: PagerState,
    modifier: Modifier, selectedColor: Color, unselectedColor: Color
) {
    Row(modifier = modifier) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                selectedColor else unselectedColor
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .size(10.dp)
                    .background(color)
            )
        }
    }
}
