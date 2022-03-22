package com.rawgvideogames.ui.screenshots

data class ScreenshotsState(
    val screenshots : List<String> = emptyList(),
    val isLoading : Boolean = false,
    val error : String = ""
)
