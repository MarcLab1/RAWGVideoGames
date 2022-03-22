package com.rawgvideogames.ui.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rawgvideogames.ui.gamedetail.GameDetailPage
import com.rawgvideogames.ui.games.GamesPage
import com.rawgvideogames.ui.games.GamesViewModel
import com.rawgvideogames.ui.screenshots.ScreenshotHorizontalPager
import com.rawgvideogames.util.Routes

@Composable
fun Navigation(nav: NavController, gamesViewModel: GamesViewModel) {

    NavHost(navController = nav as NavHostController, startDestination = Routes.GAMES)
    {
        composable(Routes.GAMES)
        {
            GamesPage(nav = nav, gamesViewModel = gamesViewModel)
        }
        composable("${Routes.GAMEDETAIL}/{id}")
        {
            GameDetailPage(nav = nav)
        }
        composable("${Routes.SCREENSHOTS}/{id}/{index}")
        {
            var index = it.arguments?.getString("index")
            ScreenshotHorizontalPager(index ?: "0")
        }
    }
}