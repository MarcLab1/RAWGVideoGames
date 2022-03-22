package com.rawgvideogames.domain.model

import com.rawgvideogames.data.network.gamedetail.Rating

data class GameDetail(
    val id: Int,
    val name: String,
    val description: String,
    val released: String,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val website: String,
    val rating: Double,
    val ratings: List<Rating>,
    val playtime: Int,
    val platforms : List<String>,
    val genres: String,
    val publishers : String
)