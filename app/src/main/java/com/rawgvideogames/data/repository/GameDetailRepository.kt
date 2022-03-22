package com.rawgvideogames.data.repository

import com.rawgvideogames.domain.model.GameDetail

interface GameDetailRepository {

    suspend fun getGameDetail(id: String) : GameDetail
}