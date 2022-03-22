package com.rawgvideogames.ui.appbar

import com.rawgvideogames.util.Routes

data class AppBarState(
    val title : String = Routes.GAMES,
    val currentRoute: String = Routes.GAMES,
    val isDarkTheme: Boolean = false,
    val isSearchOpened : Boolean = false
)

