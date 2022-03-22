package com.rawgvideogames.data.repository

import com.rawgvideogames.data.network.game.ApiService

class ScreenshotsRepository_Impl(
    private val apiService: ApiService
): ScreenshotsRepository {
    override suspend fun getScreenshots(game_pk: String): List<String> {
        return apiService.getScreenshots(game_pk).toScreenshots()
    }
}