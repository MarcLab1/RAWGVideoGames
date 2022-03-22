package com.rawgvideogames.data.repository

import com.rawgvideogames.data.network.game.ApiService
import com.rawgvideogames.domain.model.Movie

class MoviesRepository_Impl(private val apiService : ApiService) : MoviesRepository {
    override suspend fun getMovies(id: String): List<Movie> {
        return apiService.getMovies(id).toMovies()
    }
}