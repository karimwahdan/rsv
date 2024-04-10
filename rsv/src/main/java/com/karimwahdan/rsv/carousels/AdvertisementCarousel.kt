@file:OptIn(ExperimentalFoundationApi::class)

package com.karimwahdan.rsv.carousels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.advertisements.Advertisement
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AdvertisementCarousel(
    placeholderImage:Int,
    errorImage:Int,
    dotColor: Color,
    advertisements: List<Advertisement>?,
    autoScrollDuration: Long = 3000L,
) {
    //var hasImages=0
    if (!advertisements.isNullOrEmpty()){
        val pageCount: Int = advertisements.size
        val pagerState: PagerState = rememberPagerState(
            0,
            0.0f
        ) { pageCount }
        val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
        if (isDragged.not()) {
            with(pagerState) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            HorizontalPager(
                contentPadding = PaddingValues(0.dp),
                modifier= Modifier
                    .padding(0.dp)
                    //.weight(1f)
                    .fillMaxWidth()
                    .height(280.dp)
                ,
                beyondBoundsPageCount = pageCount,
                state = pagerState
            ) { page ->
                AdvertisementCarouselItem(
                    placeholderImage,
                    errorImage,
                    advertisements[page],
                    modifier = Modifier.padding(0.dp).carouselTransition(page,pagerState)
                )
            }
            DotIndicators(
                pageCount = pageCount,pagerState = pagerState,
                modifier = Modifier,selectedColor = Color.Black,
                unselectedColor = dotColor
            )
        }
    }
}

@Composable
fun AdvertisementCarouselItem(
    placeholderImage:Int,
    errorImage:Int,
    advertisement: Advertisement?,
    modifier: Modifier) {
    if (advertisement!=null){
        if (advertisement.image!=null){
            Image(
                rememberAsyncImagePainter( ImageRequest
                    .Builder(LocalContext.current).data(advertisement.image)
                    .apply(block =fun ImageRequest.Builder.(){
                        placeholder(placeholderImage).error(errorImage)
                    }).scale(scale = Scale.FILL).build()),
                modifier = modifier
                    .fillMaxSize(),
                contentDescription = null
            )
        }
    }

}
