package com.rawgvideogames.ui.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rawgvideogames.util.Routes
import com.rawgvideogames.ui.common.GameItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rawgvideogames.util.Constants

@Composable
fun GamesPage(nav: NavController, gamesViewModel: GamesViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var gamesState = gamesViewModel.gamesState

        Box(modifier = Modifier.fillMaxSize())
        {
            LazyColumn(modifier = Modifier.fillMaxWidth())
            {
                itemsIndexed(gamesState.games)
                { index, game ->
                    gamesViewModel.updateCurrentScrollPosition(index)
                    GameItem(index, game, {
                        nav.navigate(route = Routes.GAMEDETAIL + "/${game.id}")
                    })
                    if (((index + 1) >= gamesState.games.size) && !gamesState.isLoading && !gamesState.isLastPage)
                        gamesViewModel.getMoreGames()
                }
            }

            if (gamesState.isLoading)
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            if (gamesState.games.size == 0 && gamesState.error.isNotEmpty()) {
                Text(
                    gamesState.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
        }
    }
}

/*
@Composable
fun WhereAmI(gamesViewModel: GamesViewModel) {
    Column() {
        Text(gamesViewModel.gamesState.isLastPage.toString())
        Text(gamesViewModel.gamesState.pageNumber.toString())
        Text(gamesViewModel.calls.value.toString())
        Text(gamesViewModel.currentScrollPosition.toString())
        Text(gamesViewModel.gamesState.games.size.toString())
    }
}
*/


