package com.rawgvideogames.data.network.game

import com.rawgvideogames.data.network.gamedetail.GameDetailDto
import com.rawgvideogames.data.network.movies.MovieDto
import com.rawgvideogames.data.network.screenshots.ScreenshotsDto
import com.rawgvideogames.util.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /*
    "id": 3498,
    "name": "Grand Theft Auto V",
    */

    @Headers(Constants.HEADER1, Constants.HEADER2)
    @GET("games")
    suspend fun getGames(
        @Query("search") query: String,
        @Query("page") page: String,
        @Query("page_size") pageSize: String,
        @Query("key") key: String = Constants.API_KEY
    ): ResponseDto

    @Headers(Constants.HEADER1, Constants.HEADER2)
    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: String,
        @Query("key") key: String = Constants.API_KEY
    ): GameDetailDto

    @Headers(Constants.HEADER1, Constants.HEADER2)
    @GET("/games/{game_pk}/screenshots")
    suspend fun getScreenshots(
        @Path("game_pk") game_pk: String,
        @Query("page") page: String ="1",
        @Query("page_size") pageSize: String = "10",  //most games I checked had 6 screenshots, so setting an explicit value may be unnecessary, but I use it for safety
        @Query("key") key: String = Constants.API_KEY,
    ): ScreenshotsDto

    @Headers(Constants.HEADER1, Constants.HEADER2)
    @GET("games/{id}/movies")
    suspend fun getMovies(
        @Path("id") id: String,
        @Query("key") key: String = Constants.API_KEY
    ): MovieDto
}