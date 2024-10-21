package com.example.tanorami.heroes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.heroes.presentation.HeroesListViewModelImpl
import com.example.tanorami.heroes.presentation.models.HeroesListScreenEvents
import com.example.tanorami.heroes.presentation.models.HeroesListScreenSideEffects
import com.example.tanorami.heroes.presentation.models.HeroesListScreenUiState
import com.example.tanorami.heroes.ui.components.HeroItem
import com.example.tanorami.heroes.ui.components.HeroesListTopAppBar
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroFragment

@Composable
fun HeroesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: HeroesListViewModelImpl = viewModel(factory = viewModelFactory),
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
            navController.navigate(R.id.action_heroesListFragment_to_settingsFragment)
            viewModel.clearEffect()
        }

        HeroesListScreenSideEffects.OnProfileScreen -> {
            navController.navigate(R.id.action_heroesListFragment_to_profileFragment)
            viewModel.clearEffect()
        }

        is HeroesListScreenSideEffects.OnInfoAboutHeroScreen -> {
            navController.navigate(
                R.id.action_heroesListFragment_to_infoAboutHeroFragment,
                InfoAboutHeroFragment.newInstance(sideEffect.idHero)
            )
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@Composable
private fun HeroesListScreenContent(
    uiState: HeroesListScreenUiState,
    onEvent: (HeroesListScreenEvents) -> Unit,
) {
    Column {
        HeroesListTopAppBar(
            uiState = uiState,
            onEvent = onEvent::invoke
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
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