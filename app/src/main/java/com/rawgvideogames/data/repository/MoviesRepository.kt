package com.rawgvideogames.data.repository

import com.rawgvideogames.domain.model.Movie

interface MoviesRepository {

    suspend fun getMovies(id: String) : List<Movie>
}