package com.example.tanorami.heroes.ui

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
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.heroes.presentation.HeroesListViewModel
import com.example.tanorami.heroes.presentation.models.HeroesListScreenEvents
import com.example.tanorami.heroes.presentation.models.HeroesListScreenSideEffects
import com.example.tanorami.heroes.presentation.models.HeroesListScreenUiState
import com.example.tanorami.heroes.ui.components.HeroItem
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroNavArguments
import com.example.tanorami.settings.ui.SettingsRoute

@Composable
fun HeroesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: HeroesListViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(initial = null).value

    HeroesListScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffect) {
        HeroesListScreenSideEffects.OnSettingsScreen -> {
            navController.navigate(route = SettingsRoute)
            viewModel.clearEffect()
        }

        is HeroesListScreenSideEffects.OnInfoAboutHeroScreen -> {
            navController.navigate(route = InfoAboutHeroNavArguments(sideEffect.idHero))
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .semantics { traversalIndex = 0F },
            inputField = {
                SearchBarDefaults.InputField(
                    query = uiState.searchTextField.value,
                    onQueryChange = { onEvent(HeroesListScreenEvents.SearchTextChanged(it)) },
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
                            modifier = Modifier.clickable { onEvent(HeroesListScreenEvents.OnSettingsScreen) },
                            imageVector = Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    colors = inputFieldColors(
                        cursorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            },
            expanded = false,
            onExpandedChange = { },
            content = { }
        )

        LazyVerticalGrid(
            modifier = Modifier.semantics { traversalIndex = 1F },
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 72.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                if (uiState.searchTextField.value.isEmpty()) uiState.heroesList else uiState.filteredHeroesList
            ) { hero ->
                HeroItem(
                    hero = hero,
                    onClick = { onEvent(HeroesListScreenEvents.OnInfoAboutHeroScreen(hero.id)) },
                )
            }
        }
    }
}