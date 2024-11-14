package com.example.heroes_list.heroes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.heroes_list.R
import com.example.heroes_list.heroes.presentation.HeroesListViewModel
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenEvents
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenSideEffects
import com.example.heroes_list.heroes.presentation.models.HeroesListScreenUiState
import com.example.heroes_list.heroes.ui.components.HeroItem

@Composable
fun HeroesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: HeroesListViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(null).value

    HeroesListScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffect) {
        HeroesListScreenSideEffects.OnSettingsScreen -> {
            //navController.navigate(route = SettingsRoute)
            viewModel.clearEffect()
        }

        is HeroesListScreenSideEffects.OnInfoAboutHeroScreen -> {
            //navController.navigate(route = InfoAboutHeroNavArguments(sideEffect.idHero))
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeroesListScreenContent(
    uiState: HeroesListScreenUiState,
    onEvent: (HeroesListScreenEvents) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.Companion
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = uiState.searchTextField.value,
                    onQueryChange = {
                        onEvent(HeroesListScreenEvents.SearchTextChanged(it))
                    },
                    onSearch = { },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_name_hero)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.Companion.clickable {
                                onEvent(
                                    HeroesListScreenEvents.OnSettingsScreen
                                )
                            },
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    colors = SearchBarDefaults.inputFieldColors(
                        cursorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            },
            expanded = false,
            onExpandedChange = { },
            content = { }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 72.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                if (uiState.searchTextField.value.isEmpty()) uiState.heroesList else uiState.filteredHeroesList
            ) { hero ->
                HeroItem(
                    heroModel = hero,
                    onClick = {
                        onEvent(HeroesListScreenEvents.OnInfoAboutHeroScreen(hero.id))
                    },
                )
            }
        }
    }
}