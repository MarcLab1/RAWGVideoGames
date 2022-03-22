package com.rawgvideogames.ui.screenshots

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.rawgvideogames.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenshotHorizontalPager(indexString: String) {

    var screenshotsViewModel: ScreenshotsViewModel = hiltViewModel()
    var screenshotsState = screenshotsViewModel.screenshotsState
    var index = indexString.toIntOrNull() ?: 0

    Box(modifier = Modifier.fillMaxSize())
    {
        HorizontalPager(screenshotsState.screenshots.size) { page ->
            Image(
                painter = rememberImagePainter(
                    data = screenshotsState.screenshots.get((index + page) % screenshotsState.screenshots.size),
                    //builder = { placeholder(R.drawable.placeholder) }
                ),
                contentDescription = "Screenshot",
                modifier = Modifier
                    .padding(10.dp)
                    .width(300.dp)
                    .height(170.dp)
            )
        }
        if (screenshotsState.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        if (screenshotsState.error.isNotEmpty())
            Text(
                screenshotsState.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 10.dp, end = 10.dp)
            )
    }
}