package com.rawgvideogames.ui.appbar

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val app: Application,
    private val booldataStoreKey: Preferences.Key<Boolean>
) : ViewModel() {

    var appBarState by mutableStateOf(AppBarState())

    init {
        viewModelScope.launch {
            dataStore.edit {
                appBarState = appBarState.copy(isDarkTheme = it[booldataStoreKey] ?: false)
            }
        }
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
        appBarState = appBarState.copy(isSearchOpened = !appBarState.isSearchOpened)
    }
}