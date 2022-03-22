package com.rawgvideogames.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.rawgvideogames.R
import com.rawgvideogames.domain.model.Game
import com.rawgvideogames.util.Constants

@Composable
fun GameItem(index: Int, game: Game, onClick: () -> Unit) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .drawBehind {
            val width = .4.dp.value * density
            val y = size.height - width / 2
            drawLine(
                Color.LightGray,
                Offset(0f, y),
                Offset(size.width, y),
                width
            )
        }
        .padding(start = 10.dp, end = 10.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            Row(
                modifier = Modifier.fillMaxWidth(.85f),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column() {
                    Image(
                        painter = rememberImagePainter(
                            data = game.background_image,
                            builder = {
                                transformations(CircleCropTransformation())
                                placeholder(R.drawable.placeholder)
                            },
                            ),
                        contentDescription = game.name,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 20.dp)
                    )
                }
                Column()
                {
                    Text(
                        text =
                        if (game.name.isNotEmpty()) game.name else Constants.QUESTIONMARK,
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )

                    Text(
                        text =
                        if (game.genres.isNotEmpty()) game.genres else Constants.QUESTIONMARK,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )

                    Text(
                        text =
                        if (game.released.isNotEmpty()) game.released else Constants.QUESTIONMARK,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                }
            }
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                GameRatingBox(game.rating)
            }
        }
    }
}

@Composable
fun GameRatingBox(rating: Double) {
    var color: Int
    var shape: Shape
    if (rating >= 4.0) {
        color = R.color.LimeGreen
        shape = RoundedCornerShape(1.dp)
    } else if (rating >= 3.0) {
        color = R.color.CornflowerBlue
        shape = CircleShape
    } else if (rating >= 2.0) {
        color = R.color.OrangeRed
        shape = AbsoluteCutCornerShape(5.dp)
    } else if (rating >= 1.0) {
        color = R.color.Red
        shape = CutCornerShape(10.dp)
    } else {
        color = R.color.LimeGreen
        shape = RoundedCornerShape(1.dp)
    }

    Box(
        modifier = Modifier
            .height(35.dp)
            .width(35.dp)
            .background(color = colorResource(color), shape = shape),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

