package com.rawgvideogames.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.rawgvideogames.ui.appbar.AppBarViewModel
import com.rawgvideogames.ui.appbar.MyAppBar
import com.rawgvideogames.ui.games.GamesViewModel

@Composable
fun MyScaffold(appBarViewModel: AppBarViewModel) {
    var scaffoldState = rememberScaffoldState()
    var nav = rememberNavController()
    val gamesViewModel: GamesViewModel = hiltViewModel()

    Scaffold(
        topBar = { MyAppBar(
                appBarViewModel = appBarViewModel,
                gamesViewModel = gamesViewModel,
                nav = nav)},
        content = { Navigation(nav, gamesViewModel) },
        scaffoldState = scaffoldState
    )
}

