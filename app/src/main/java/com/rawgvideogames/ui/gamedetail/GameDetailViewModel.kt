package com.rawgvideogames.ui.gamedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawgvideogames.domain.usecase.GetGameDetailUseCase
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailUseCase: GetGameDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    var gameDetailState by mutableStateOf(GameDetailState())
        private set

    init {
        savedStateHandle.get<String>(Constants.ID)?.let {
            getGameDetail(id = it)
        }
    }

    fun getGameDetail(id: String) {
        gameDetailUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    gameDetailState = gameDetailState.copy(gameDetail = result.data, isLoading = false, error = "")
                }
                is Resource.Error -> {
                    gameDetailState = gameDetailState.copy(isLoading = false, error = result.message ?: Constants.ERROR_MSG)
                }
                is Resource.Loading -> {
                    gameDetailState = gameDetailState.copy(isLoading = true, error = "")
                }
            }
        }.launchIn(viewModelScope)
    }
}