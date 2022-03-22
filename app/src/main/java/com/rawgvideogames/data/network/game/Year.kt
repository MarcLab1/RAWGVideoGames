package com.rawgvideogames.data.network.game

data class Year(
    val from: Int?,
    val to: Int?,
    val filter: String?,
    val decade: Int?,
    val years: List<YearX>?,
    val nofollow: Boolean?,
    val count: Int?
)