package com.rawgvideogames.data.network.gamedetail

import com.google.gson.annotations.SerializedName
import com.rawgvideogames.data.network.game.Platform
import com.rawgvideogames.domain.model.GameDetail

data class GameDetailDto(
    val id: Int?,
    val slug: String?,
    val name: String?,
    val name_original: String?,
    val description: String?,
    val metacritic: Int?,
    val metacritic_platforms: List<MetacriticPlatform>?,
    val released: String?,
    val tba: Boolean?,
    val updated: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String?,

    val website: String?,
    val rating: Double?,
    val rating_top: Int?,
    val ratings: List<Rating>? = listOf(),
    val reactions: Reactions?,
    val added: Int?,
    val added_by_status: AddedByStatus?,
    val playtime: Int?,
    val screenshots_count: Int?,
    val movies_count: Int?,
    val creators_count: Int?,
    val achievements_count: Int?,
    val parent_achievements_count: Int?,
    val reddit_url: String?,
    val reddit_name: String?,
    val reddit_description: String?,
    val reddit_logo: String?,
    val reddit_count: Int?,
    val twitch_count: Int?,
    val youtube_count: Int?,
    val reviews_text_count: Int?,
    val ratings_count: Int?,
    val suggestions_count: Int?,
    val alternative_names: List<String>?,
    val metacritic_url: String?,
    val parents_count: Int?,
    val additions_count: Int?,
    val game_series_count: Int?,
    val user_game: Any?,
    val reviews_count: Int?,
    val saturated_color: String?,
    val dominant_color: String?,
    val parent_platforms: List<ParentPlatform>?,
    val platforms: List<PlatformXX>? = listOf(),
    val stores: List<Store>?,
    val developers: List<Developer>?,
    val genres: List<Genre>?,
    val tags: List<Tag>?,
    val publishers: List<Publisher>?,
    val esrb_rating: EsrbRating?,
    val clip: Any?,
    val description_raw: String?
) {
    fun toGameDetail(): GameDetail {
        return GameDetail(
            id ?: 0,
            name ?: "?",
            description_raw ?: "?",
            released ?: "?",
            backgroundImage?: "",
            backgroundImageAdditional?: "",
            website?: "",
            rating ?: 0.0,
            ratings ?: listOf(),
            playtime ?: 0,
            getPlatformsList(platforms ?: listOf()),
            getGenresString(genres ?: listOf()),
            getPublishersString(publishers ?: listOf())
        )
    }

    fun getPlatformsList(list: List<PlatformXX>): List<String> {
        return list.map{ it.platform?.name ?: "" }
    }

    fun getGenresString(list: List<Genre>): String{
        return list.map{ it.name}.joinToString(", ")
    }

    fun getPublishersString(list: List<Publisher>): String{
        return list.map{ it.name}.joinToString(", ")
    }
}