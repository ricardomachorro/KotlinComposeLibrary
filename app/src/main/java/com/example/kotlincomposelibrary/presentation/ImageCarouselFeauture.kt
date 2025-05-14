package com.example.kotlincomposelibrary.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.kotlincomposelibrary.R
import kotlin.math.absoluteValue


@Composable
fun ImageCarouselFeauture(modifier: Modifier = Modifier){

    val imageList = listOf(
        R.drawable.food_banner_1,
        R.drawable.food_banner_2,
        R.drawable.food_banner_3
    )

    val pagerState = rememberPagerState { imageList.size }

    Column(
        modifier
            .defaultMinSize(minHeight = 300.dp)
            .fillMaxWidth()
    ){

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
             pageSpacing = 10.dp,
            contentPadding = PaddingValues(100.dp)
        ) {

            page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentDescription = "Image $page",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .graphicsLayer{
                        val pageOffset =
                            (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue

                        lerp(
                            start = 75.dp,
                            stop = 100.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleY = scale / 100.dp
                        }
                    },
                contentScale = ContentScale.Crop
            )
        }
    }

}