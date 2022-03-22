package com.rawgvideogames.ui.screenshots

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawgvideogames.domain.usecase.GetScreenshotsUseCase
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScreenshotsViewModel @Inject constructor(
    private val screenshotsUseCase: GetScreenshotsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var screenshotsState by mutableStateOf(ScreenshotsState())
        private set

    init {
        savedStateHandle.get<String>(Constants.ID)?.let {
            getScreenshots(id = it)
        }
    }

    fun getScreenshots(id: String) {
        screenshotsUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    screenshotsState = ScreenshotsState(
                        screenshots = result.data ?: emptyList<String>(),
                        isLoading = false,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    screenshotsState = screenshotsState.copy(
                        isLoading = false,
                        error = result.message ?: Constants.ERROR_MSG
                    )
                }
                is Resource.Loading -> {
                    screenshotsState = screenshotsState.copy(isLoading = true, error = "")
                }
            }
        }.launchIn(viewModelScope)
    }
}