package com.rawgvideogames.ui.gamedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.rawgvideogames.data.network.gamedetail.Rating
import com.rawgvideogames.R
import com.rawgvideogames.ui.screenshots.ScreenshotsViewModel
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Routes

@Composable
fun GameDetailPage(
    nav: NavController,
) {
    var gameDetailViewModel: GameDetailViewModel = hiltViewModel()
    var gameDetailState = gameDetailViewModel.gameDetailState


    var screenshotsViewModel: ScreenshotsViewModel = hiltViewModel()
    var screenshots = screenshotsViewModel.screenshotsState.screenshots

    Box(modifier = Modifier.fillMaxSize())
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item()
            {
                var gameDetail = gameDetailState.gameDetail
                if (gameDetail != null) {
                    GameTitleDetails(
                        id = gameDetail.id,
                        title = gameDetail.name,
                        description = gameDetail.description,
                        backgroundImage = gameDetail.backgroundImage
                    )

                    GameInfo(
                        releaseDate = gameDetail.released,
                        genres = gameDetail.genres,
                        publishers = gameDetail.publishers
                    )

                    GameScreenshots(screenshots = screenshots, OnScreenshotClicked = {
                        nav.navigate(Routes.SCREENSHOTS + "/${gameDetail.id}" + "/${it}")
                    })

                    GameRatings(ratings = gameDetail.ratings)

                    GamePlatforms(platforms = gameDetail.platforms)
                }
            }
        }
        if (gameDetailState.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        if (gameDetailState.error.isNotEmpty())
            Text(
                gameDetailState.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 10.dp, end = 10.dp)
            )
    }
}


@Composable
fun GameTitleDetails(id: Int, title: String, description: String, backgroundImage: String) {
    //Text(text = id.toString(), style = MaterialTheme.typography.h1)
    Text(text = title, style = MaterialTheme.typography.h1)
    Image(
        painter = rememberImagePainter(
            data = backgroundImage,
            //builder = { placeholder(R.drawable.placeholder) },
            //builder = { size(OriginalSize) }, --> some of the images are too large and this causes the app to crash
        ),
        contentDescription = title,
        modifier = Modifier
            .padding(10.dp)
            .width(300.dp)
            .height(170.dp)
    )
    Text(
        text = description,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    MyDivider()
}

@Composable
fun GameInfo(releaseDate: String, genres: String, publishers: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    )
    {
        Text(
            text = "Publisher",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
        )
        Text(
            if (publishers.isNotEmpty()) publishers else Constants.QUESTIONMARK,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Genres",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
        )
        Text(
            if (genres.isNotEmpty()) genres else Constants.QUESTIONMARK,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Release Date",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
        )
        Text(
            if (releaseDate.isNotEmpty()) releaseDate else Constants.QUESTIONMARK,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.h6
        )
    }
    MyDivider()
}


@Composable
fun GameRatings(ratings: List<Rating>) {
    if (ratings.size != 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        )
        {

            Text(
                text = "Ratings",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
            RatingItem(ratings = ratings.sortedByDescending { rating -> rating.id })
        }
        MyDivider()
    }
}

@Composable
fun GameScreenshots(screenshots: List<String>, OnScreenshotClicked: (index: Int) -> Unit) {
    if (screenshots.size != 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        )
        {
            Text(
                text = "Screenshots",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
            LazyRow()
            {
                itemsIndexed(screenshots)
                { index, image ->
                    Image(
                        painter = rememberImagePainter(
                            data = image
                        ),
                        contentDescription = "Screenshot${index + 1}",
                        modifier = Modifier
                            .width(213.dp)
                            .height(120.dp)
                            .padding(start = 10.dp, end = 10.dp)
                            .clickable {
                                OnScreenshotClicked(index)
                            }
                    )
                }
            }
        }
        MyDivider()
    }
}

@Composable
fun GamePlatforms(platforms: List<String>) {
    if (platforms.size != 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        )
        {
            Text(
                text = "Platforms", style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                platforms.forEach { platform ->
                    PlatformItem(platform = platform)
                }
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(color = Color.LightGray, thickness = .5.dp)
}

@Composable
fun RatingItem(ratings: List<Rating>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Column(horizontalAlignment = Alignment.End) {
                for (rating in ratings) {
                    Text(
                        text = rating.title.toString().replaceFirstChar { ch -> ch.uppercase() },
                        modifier = Modifier.padding(start = 5.dp),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            Column(horizontalAlignment = Alignment.Start) {
                for (rating in ratings) {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        rating.id?.let {
                            rating.percent?.let { it1 ->
                                MyRectangle(
                                    id = it,
                                    percent = it1
                                )
                            }
                        }
                        Text(
                            text = rating.count.toString(),
                            modifier = Modifier.padding(start = 5.dp),
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlatformItem(
    platform: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(5.dp)
    ) {
        Text(
            text = platform,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun MyRectangle(id: Int, percent: Double) {
    var color: Int
    when (id) {
        1 -> color = R.color.Red  //skip
        2 -> color = R.color.LimeGreen//nothing
        3 -> color = R.color.Orange //meh
        4 -> color = R.color.DodgerBlue  //rec
        5 -> color = R.color.LimeGreen  //exceptional
        else -> color = R.color.LimeGreen
    }
    var width = (percent / 100).toFloat()
    if (width >= .8)
        width = .8f
    Box(
        modifier = Modifier
            .padding(start = 5.dp)
            .fillMaxWidth(width)
            .height(10.dp)
            .clip(RectangleShape)
            .background(colorResource(id = color))
    )

}
