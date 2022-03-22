package com.rawgvideogames.data.repository

interface ScreenshotsRepository {

    suspend fun getScreenshots(game_pk: String) : List<String>
}