package com.rawgvideogames.domain.usecase

import com.rawgvideogames.data.repository.GameDetailRepository
import com.rawgvideogames.domain.model.GameDetail
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val gameDetailRepository : GameDetailRepository
) {
    operator fun invoke(id: String): Flow<Resource<GameDetail>> = flow {
        try {
            emit(Resource.Loading<GameDetail>())
            val gameDetail = gameDetailRepository.getGameDetail(id)
            emit(Resource.Success<GameDetail>(gameDetail))
        } catch(e: HttpException) {
            emit(Resource.Error<GameDetail>(e.localizedMessage ?: Constants.ERROR_MSG))
        } catch(e: IOException) {
            emit(Resource.Error<GameDetail>(Constants.NO_INTERNET_ERROR_MSG))
        }
    }
}