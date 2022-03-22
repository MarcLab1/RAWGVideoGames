package com.rawgvideogames.data.repository

import com.rawgvideogames.data.network.game.ApiService
import com.rawgvideogames.domain.model.Response

class GamesRepository_Impl (private val apiService: ApiService) : GamesRepository {

    override suspend fun getGames(query: String, page: String, pageSize: String): Response {
       return apiService.getGames(query, page, pageSize).toResponse()}
}