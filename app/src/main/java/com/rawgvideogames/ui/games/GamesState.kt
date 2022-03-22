package com.rawgvideogames.ui.games

import com.rawgvideogames.domain.model.Game

data class GamesState(
    val games: List<Game> = emptyList(),
    val pageNumber : Int = 1,
    val isLoading : Boolean = false,
    val error : String = "",
    val query: String = "",
    val isLastPage : Boolean = false
)
