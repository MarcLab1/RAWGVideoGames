package com.rawgvideogames.ui.games

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawgvideogames.domain.model.Game
import com.rawgvideogames.domain.usecase.GetGamesUseCase
import com.rawgvideogames.util.Constants
import com.rawgvideogames.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCase: GetGamesUseCase
) : ViewModel() {

    var gamesState by mutableStateOf(GamesState())
        private set

    //var calls = mutableStateOf(0)
    private var currentScrollPosition = 0
    init {
        resetSearch()
    }

    //this is a new search
    fun resetSearch() {
        currentScrollPosition = 0
        gamesState = gamesState.copy(
            games = emptyList(),
            pageNumber = 1,
            isLoading = false,
            error = "",
            isLastPage = false
        )
        getGames()
    }

    fun getGames() {
       // calls.value++
        gamesUseCase(gamesState.query, gamesState.pageNumber.toString()).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val newGames = gamesState.games + result.data?.games as List<Game>
                    val nextIsNull = result.data.isLastPage
                    gamesState = gamesState.copy(
                        games = newGames,
                        pageNumber = gamesState.pageNumber + 1,
                        isLoading = false,
                        error = "",
                        isLastPage = nextIsNull
                    )
                }
                is Resource.Error -> {
                    gamesState = gamesState.copy(
                        isLoading = false,
                        error = result.message ?: Constants.ERROR_MSG
                    )
                }
                is Resource.Loading -> {
                    gamesState = gamesState.copy(isLoading = true, error = "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMoreGames()
    {
        //prevents multiple recompositions
        if ((currentScrollPosition + 1) >= ((gamesState.pageNumber-1) * Constants.PAGE_SIZE_INT)) {
            getGames()
        }
    }

    fun updateQuery(newQuery: String) {
        gamesState = gamesState.copy(query = newQuery)
    }

    fun updateCurrentScrollPosition(newPosition : Int)
    {
        currentScrollPosition = newPosition
    }
}
