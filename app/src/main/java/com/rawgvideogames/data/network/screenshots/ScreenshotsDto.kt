package com.rawgvideogames.data.network.screenshots

data class ScreenshotsDto(
    val count: Int?,
    val next: Any?,
    val previous: Any?,
    val results: List<Result>? = listOf()
)

{
    fun toScreenshots() : List<String> {

        return results?.map { it.image ?: "" } ?: listOf()
    }
}