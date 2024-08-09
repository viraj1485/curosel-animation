package com.example.carousel_animation.curosel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> HorizontalCarousel(
    items: List<T>,
    initialPage: Int = 0,
    modifier: Modifier=Modifier,
    pageSpacing: Dp = 10.dp,
    paddingValues: PaddingValues = PaddingValues(85.dp),
    content: @Composable (position: Int, item: T) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = initialPage){
        items.size
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = paddingValues,
        pageSpacing = pageSpacing,
    ) { page ->
        Card(modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {

                val pageOffset =
                    ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                val alphaLerp = lerp(
                    start = 0.4f, stop = 1f, fraction = (1f - pageOffset.coerceIn(0f, 1f))
                )
                val scaleLerp = lerp(
                    start = 0.5f, stop = 1f, fraction = (1f - pageOffset.coerceIn(0f, .5f))
                )
                alpha = alphaLerp
                scaleX = scaleLerp
                scaleY = scaleLerp
            })
        {
            content(page, items[page])
        }
    }
}