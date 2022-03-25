package com.rawgvideogames.ui.appbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawgvideogames.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val booldataStoreKey: Preferences.Key<Boolean>,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var appBarState by mutableStateOf(AppBarState())

    init {
        viewModelScope.launch {
            dataStore.edit {
                appBarState = appBarState.copy(isDarkTheme = it[booldataStoreKey] ?: false)
            }
        }

        //restore if search was opened
        if(savedStateHandle.get<Boolean>(Constants.SEARCH_OPENED) == true)
            toggleSearchOpened()
    }

    fun toggleDarkTheme() {
        viewModelScope.launch {
            dataStore.edit {
                val bool = appBarState.isDarkTheme ?: false
                appBarState = appBarState.copy(isDarkTheme =!bool)
                it[booldataStoreKey] = !bool
            }
        }
    }

    fun toggleSearchOpened()
    {
        val newIsSearchOpened = !appBarState.isSearchOpened
        appBarState = appBarState.copy(isSearchOpened = newIsSearchOpened)
        savedStateHandle.set(Constants.SEARCH_OPENED, newIsSearchOpened)
    }
}