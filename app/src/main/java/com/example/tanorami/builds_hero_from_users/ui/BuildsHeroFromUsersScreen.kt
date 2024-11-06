package com.example.tanorami.builds_hero_from_users.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.builds_hero_from_users.presentation.BuildsHeroFromUsersViewModel
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenEvents
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenSideEffects
import com.example.tanorami.builds_hero_from_users.presentation.models.BuildsHeroFromUsersScreenUiState
import com.example.tanorami.builds_hero_from_users.ui.components.BuildsListLazyColumn
import com.example.tanorami.teams.ui.components.EmptyListScreen
import com.example.tanorami.teams.ui.components.ErrorScreen
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserNavArguments
import kotlinx.serialization.Serializable

@Serializable
data class BuildsHeroFromUsersNavArguments(val idHero: Int)

@Composable
fun BuildsHeroFromUsersScreen(
    navArguments: BuildsHeroFromUsersNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: BuildsHeroFromUsersViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    BuildsHeroFromUsersScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent,
    )

    when (sideEffects) {
        is BuildsHeroFromUsersScreenSideEffects.OnViewingBuildHeroFromUserScreen -> {
            navController.navigate(route = ViewingBuildHeroFromUserNavArguments(idBuild = sideEffects.idBuild))
            viewModel.clearEffect()
        }

        BuildsHeroFromUsersScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(
                    BuildsHeroFromUsersScreenEvents.GetBuildsHeroList(
                        navArguments.idHero
                    )
                )
            }

            else -> {}
        }
    }
}

@Composable
private fun BuildsHeroFromUsersScreenContent(
    uiState: BuildsHeroFromUsersScreenUiState,
    onEvent: (BuildsHeroFromUsersScreenEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.builds_for_hero, uiState.hero?.name ?: ""),
                onBack = { onEvent(BuildsHeroFromUsersScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isError -> ErrorScreen()
            uiState.buildsList.isEmpty() -> EmptyListScreen()
            uiState.buildsList.isNotEmpty() -> {
                BuildsListLazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    buildsList = uiState.buildsList,
                    onBuildClick = {
                        onEvent(
                            BuildsHeroFromUsersScreenEvents.OnViewingBuildHeroFromUserScreen(
                                it
                            )
                        )
                    }
                )
            }
        }
    }
}