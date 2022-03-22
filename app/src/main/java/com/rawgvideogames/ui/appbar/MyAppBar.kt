package com.rawgvideogames.ui.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rawgvideogames.ui.games.GamesViewModel
import com.rawgvideogames.util.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAppBar(
    appBarViewModel: AppBarViewModel,
    gamesViewModel: GamesViewModel,
    nav: NavController
) {
    var keyboardController = LocalSoftwareKeyboardController.current
    val navBackStackEntry = nav.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route.toString()

    if (currentRoute.startsWith(Routes.GAMEDETAIL) || currentRoute.startsWith(Routes.SCREENSHOTS)) {
        GameDetailAppBar(
            title = currentRoute,
            onBackClicked = {
                nav.navigateUp()
            },
            onDarkModeClicked = {
                appBarViewModel.toggleDarkTheme()
            }
        )
    } else {
        GamesAppBar(
            isSearchOpened = appBarViewModel.appBarState.isSearchOpened,
            query = gamesViewModel.gamesState.query,
            onTextChange = {
                gamesViewModel.updateQuery(it)
            },
            onCloseSearchClicked = {
                appBarViewModel.toggleSearchOpened()
                gamesViewModel.resetSearch()
            },
            onSearch = {
                keyboardController?.hide()
                gamesViewModel.resetSearch()
            },
            onOpenSearchClicked = {
                appBarViewModel.toggleSearchOpened()
            },
            onDarkModeClicked = {
                appBarViewModel.toggleDarkTheme()
            }
        )
    }
}

@Composable
fun GameDetailAppBar(
    title: String, //I keep this around for testing
    onBackClicked: () -> Unit,
    onDarkModeClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()

    TopAppBar()
    {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row()
            {
                IconButton(onClick = { scope.launch { onBackClicked() } }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
            Row()
            {
                IconButton(onClick = { onDarkModeClicked() }) {
                    Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Theme")
                }
            }
        }
    }
}

@Composable
fun GamesAppBar(
    isSearchOpened: Boolean,
    query: String,
    onTextChange: (String) -> Unit,
    onCloseSearchClicked: () -> Unit,
    onSearch: () -> Unit,
    onOpenSearchClicked: () -> Unit,
    onDarkModeClicked: () -> Unit
) {
    when (isSearchOpened) {
        false -> {
            SearchClosedAppBar(
                onOpenSearchClicked = onOpenSearchClicked,
                onDarkModeClicked = onDarkModeClicked
            )
        }
        true -> {
            SearchOpenedAppBar(
                query = query,
                onTextChange = onTextChange,
                onCloseSearchClicked = onCloseSearchClicked,
                onSearch = onSearch,
            )
        }
    }
}

@Composable
fun SearchClosedAppBar(onOpenSearchClicked: () -> Unit, onDarkModeClicked: () -> Unit) {
    TopAppBar()
    {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Row()
            {
                IconButton(onClick = { onOpenSearchClicked() }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            }
            Row()
            {
                IconButton(onClick = { onDarkModeClicked() }) {
                    Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "Theme")
                }
            }
        }
    }
}

@Composable
fun SearchOpenedAppBar(
    query: String,
    onTextChange: (String) -> Unit,
    onCloseSearchClicked: () -> Unit,
    onSearch: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = query,
            onValueChange = {
                onTextChange(it)
            },
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text(text = "Search games")
            },
            leadingIcon = {
                IconButton(onClick = {
                    onSearch()
                    focusManager.clearFocus()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (query.isEmpty()) {
                            onCloseSearchClicked()
                        } else {
                            onTextChange("")
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus()
                }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSurface
            )
        )
    }
}