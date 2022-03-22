package com.rawgvideogames.data.network.game

import android.media.tv.TvContract
import com.rawgvideogames.domain.model.Game

data class GameDto(
    val id: Int?,
    val slug: String?,
    val name: String?,
    val released: String?,
    val tba: Boolean?,
    val background_image: String?,
    val rating: Double?,
    val rating_top: Int?,
    val ratings: List<Rating>?,
    val ratings_count: Int?,
    val reviews_text_count: Int?,
    val added: Int?,
    val added_by_status: AddedByStatus?,
    val metacritic: Int?,
    val playtime: Int?,
    val suggestions_count: Int?,
    val updated: String?,
    val user_game: Any?,
    val reviews_count: Int?,
    val saturated_color: String?,
    val dominant_color: String?,
    val platforms: List<Platform>?,
    val parent_platforms: List<ParentPlatform>?,
    val genres: List<Genre>?,
    val stores: List<Store>?,
    val clip: Any?,
    val tags: List<Tag>?,
    val esrb_rating: EsrbRating?,
    val short_screenshots: List<ShortScreenshot>?
)

fun GameDto.toGame(): Game {
    return Game(
        id ?: 0,
        name ?: "?",
        released ?: "?",
        background_image ?: "",
        rating ?: 0.0,
        playtime ?: 0,
        getGenreString(genres ?: listOf()),
        getPlatformString(platforms ?: listOf()))
}

fun getGenreString(list: List<Genre>): String {
   return list.map{ it.name }.joinToString(", ")
}
fun getPlatformString(list: List<Platform>): String{
    return list.map{ it.platform?.name}.joinToString(", ")
}