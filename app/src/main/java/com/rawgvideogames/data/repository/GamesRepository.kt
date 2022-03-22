package com.rawgvideogames.data.repository

import com.rawgvideogames.domain.model.Response

interface GamesRepository {

    suspend fun getGames(query: String, page: String, pageSize: String) : Response
}