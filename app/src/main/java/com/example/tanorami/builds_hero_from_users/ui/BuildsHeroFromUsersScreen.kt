package com.example.tanorami.builds_hero_from_users.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
import com.example.tanorami.create_build_hero.ui.CreateBuildHeroFragment
import com.example.tanorami.profile.presentation.components.BuildItem
import com.example.tanorami.teams.ui.components.EmptyListScreen
import com.example.tanorami.teams.ui.components.ErrorScreen
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.viewing_users_build.ui.ViewingBuildHeroFromUserFragment

@Composable
fun BuildsHeroFromUsersScreen(
    idHero: Int = -1,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: BuildsHeroFromUsersViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    BuildsHeroFromUsersScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        BuildsHeroFromUsersScreenSideEffects.OnCreateBuildHeroScreen -> {
            navController.navigate(
                resId = R.id.action_buildsHeroListFragment_to_createBuildHeroFragment,
                CreateBuildHeroFragment.newInstance(idHero = idHero)
            )
            viewModel.clearEffect()
        }

        is BuildsHeroFromUsersScreenSideEffects.OnViewingBuildHeroFromUserScreen -> {
            navController.navigate(
                R.id.action_buildsHeroListFragment_to_viewingUsersBuildFragment,
                ViewingBuildHeroFromUserFragment.newInstance(idBuild = sideEffects.idBuild)
            )
            viewModel.clearEffect()
        }

        BuildsHeroFromUsersScreenSideEffects.OnBack -> navController.popBackStack()

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(BuildsHeroFromUsersScreenEvents.GetBuildsHeroList(idHero))
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
                title = if (uiState.hero != null) stringResource(
                    id = R.string.builds_for_hero,
                    uiState.hero.name
                ) else "",
                onBack = { onEvent(BuildsHeroFromUsersScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isError -> ErrorScreen()
            uiState.buildsList.isEmpty() -> EmptyListScreen()
            uiState.buildsList.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.buildsList) { buildHero ->
                        BuildItem(
                            buildHero = buildHero,
                            onClick = { onEvent(BuildsHeroFromUsersScreenEvents.OnViewingBuildHeroFromUserScreen(buildHero.idBuild)) }
                        )
                    }
                }
            }
        }
    }
}