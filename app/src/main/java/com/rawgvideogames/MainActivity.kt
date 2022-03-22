package com.rawgvideogames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rawgvideogames.ui.appbar.AppBarViewModel
import com.rawgvideogames.ui.common.MyScaffold
import com.rawgvideogames.presentation.theme.RAWGVideoGamesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val appbarViewModel by viewModels<AppBarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RAWGVideoGamesTheme( darkTheme = appbarViewModel.appBarState.isDarkTheme)
                //darkTheme = appbarViewModel.darkThemeDataStore.value)
            {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyScaffold(appBarViewModel = appbarViewModel)
                }
            }
        }

    }
}

