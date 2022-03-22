package com.rawgvideogames.domain.usecase

import com.rawgvideogames.data.repository.GamesRepository
import com.rawgvideogames.domain.model.Game
import com.rawgvideogames.domain.model.Response
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gamesRepository : GamesRepository
) {
    operator fun invoke(query: String, page: String): Flow<Resource<Response>> = flow {
        try {
            emit(Resource.Loading<Response>())
            val games = gamesRepository.getGames(query, page, Constants.PAGE_SIZE)
            emit(Resource.Success<Response>(games))
        } catch(e: HttpException) {
            emit(Resource.Error<Response>(e.localizedMessage ?: Constants.ERROR_MSG))
        } catch(e: IOException) {
            emit(Resource.Error<Response>(e.localizedMessage ?: Constants.NO_INTERNET_ERROR_MSG))
        }
    }
}