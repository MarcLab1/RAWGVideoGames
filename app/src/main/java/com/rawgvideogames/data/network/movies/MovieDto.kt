package com.rawgvideogames.data.network.movies

import com.rawgvideogames.domain.model.Movie

data class MovieDto(
    val count: Int?,
    val next: Any?,
    val previous: Any?,
    val results: List<Result>?
) {
    fun toMovies(): List<Movie> {
        if (results == null) return listOf()
        var list = mutableListOf<Movie>()
        list.addAll(results.map { Movie(it.data) })
        return list
    }
}