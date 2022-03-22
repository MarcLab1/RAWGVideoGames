package com.rawgvideogames.ui.gamedetail

import com.rawgvideogames.domain.model.GameDetail

data class GameDetailState(
    val gameDetail : GameDetail? = null,
    val isLoading : Boolean = false,
    val error : String = ""
)
