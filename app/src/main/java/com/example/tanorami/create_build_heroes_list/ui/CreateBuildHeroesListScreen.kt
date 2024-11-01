package com.example.tanorami.create_build_heroes_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.HeroName
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.create_build_hero.ui.CreateBuildForHeroNavArguments
import com.example.tanorami.create_build_heroes_list.presentation.CreateBuildHeroesListViewModel
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenEvents
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenSideEffects
import com.example.tanorami.create_build_heroes_list.presentation.models.CreateBuildHeroesListScreenUiState
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.serialization.Serializable

@Serializable
data object CreateBuildHeroesListNavArguments

@Composable
fun CreateBuildHeroesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: CreateBuildHeroesListViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    CreateBuildHeroesListContent(uiState = state, onEvent = viewModel::onEvent)

    when (sideEffects) {
        is CreateBuildHeroesListScreenSideEffects.OnCreateBuildForHeroScreen -> {
            navController.navigate(route = CreateBuildForHeroNavArguments(idHero = sideEffects.idHero))
            viewModel.clearEffect()
        }

        CreateBuildHeroesListScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> viewModel.onEvent(CreateBuildHeroesListScreenEvents.GetHeroesList)
            else -> {}
        }
    }
}

@Composable
private fun CreateBuildHeroesListContent(
    uiState: CreateBuildHeroesListScreenUiState,
    onEvent: (CreateBuildHeroesListScreenEvents) -> Unit,
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.choose_hero),
                onBack = { onEvent(CreateBuildHeroesListScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            columns = GridCells.Adaptive(minSize = 80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(uiState.heroesList) { hero ->
                Column(
                    modifier = Modifier
                        .width(100.dp)
                        .clickable {
                            onEvent(
                                CreateBuildHeroesListScreenEvents.OnCreateBuildForHeroScreen(
                                    hero.id
                                )
                            )
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .background(
                                when (hero.rarity) {
                                    false -> Violet
                                    true -> Orange
                                }
                            ),
                        model = hero.avatar,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    HeroName(nameHero = hero.name)
                }
            }
        }
    }
}