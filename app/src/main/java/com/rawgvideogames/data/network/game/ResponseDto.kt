package com.rawgvideogames.data.network.game

import com.google.gson.annotations.SerializedName
import com.rawgvideogames.domain.model.Game
import com.rawgvideogames.domain.model.Response

data class ResponseDto(
    val count: Int?,
    val next: String?,
    val previous: Any?,

    @SerializedName("results")
    val games: List<GameDto>?,

    val seo_title: String?,
    val seo_description: String?,
    val seo_keywords: String?,
    val seo_h1: String?,
    val noindex: Boolean?,
    val nofollow: Boolean?,
    val description: String?,
    val filters: Filters?,
    val nofollow_collections: List<String>?
)

{
    fun toGames() : List<Game>
    {
        val games =  games?.map{
            it.toGame()
        }
        return games ?: listOf()
    }

    fun toResponse() : Response
    {
        return Response(isLastPage = if(next == null) true else false, games = games?.map {
            it.toGame()
        } ?: listOf() )
    }
}