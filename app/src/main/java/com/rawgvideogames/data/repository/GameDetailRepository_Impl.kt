package com.rawgvideogames.data.repository

import com.rawgvideogames.data.network.game.ApiService
import com.rawgvideogames.domain.model.GameDetail

class GameDetailRepository_Impl(private val apiService: ApiService) : GameDetailRepository {

    override suspend fun getGameDetail(id: String): GameDetail {
        return apiService.getGameDetail(id).toGameDetail()
    }
}