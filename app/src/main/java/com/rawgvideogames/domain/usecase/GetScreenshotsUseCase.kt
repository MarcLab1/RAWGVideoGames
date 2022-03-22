package com.rawgvideogames.domain.usecase

import com.rawgvideogames.data.repository.ScreenshotsRepository
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScreenshotsUseCase
@Inject
constructor(
    private val screenshotsRepository: ScreenshotsRepository
) {
    operator fun invoke(id: String): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading<List<String>>())
            val screenshots = screenshotsRepository.getScreenshots(id)
            emit(Resource.Success<List<String>>(screenshots))
        } catch (e: HttpException) {
            emit(Resource.Error<List<String>>(e.localizedMessage ?: Constants.ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error<List<String>>(Constants.NO_INTERNET_ERROR_MSG))
        }
    }
}
