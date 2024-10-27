package com.example.tanorami.teams.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.createteam.ui.CreateTeamFragment
import com.example.tanorami.teams.presentation.TeamsListViewModel
import com.example.tanorami.teams.presentation.models.TeamsListScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsListScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsListScreenUiState
import com.example.tanorami.teams.ui.components.EmptyListScreen
import com.example.tanorami.teams.ui.components.ErrorScreen
import com.example.tanorami.teams.ui.components.TeamsListLazyColumn
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun TeamsListScreen(
    idHero: Int = -1,
    uid: String? = null,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: TeamsListViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsStateWithLifecycle().value
    val sideEffects = viewModel.uiEffect().collectAsStateWithLifecycle(initialValue = null).value

    TeamsListScreenContents(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        TeamsListScreenSideEffects.OnBack -> {
            navController.popBackStack()
        }

        TeamsListScreenSideEffects.OnCreateTeamScreen -> {
            navController.navigate(
                R.id.action_teamsListFragment_to_createTeamFragment,
                CreateTeamFragment.newInstance()
            )
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(TeamsListScreenEvents.GetNameHero(idHero))
                viewModel.onEvent(TeamsListScreenEvents.GetTeamsList(idHero, uid))
            }

            else -> {}
        }
    }
}

@Composable
private fun TeamsListScreenContents(
    uiState: TeamsListScreenUiState,
    onEvent: (TeamsListScreenEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = if (uiState.uid == "") {
                    stringResource(id = R.string.team_for_hero, uiState.nameHero)
                } else {
                    stringResource(id = R.string.team_for_uid)
                },
                onBack = { onEvent(TeamsListScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {}
            uiState.isError -> ErrorScreen(modifier = Modifier.padding(innerPadding))
            uiState.teamsList.isEmpty() -> EmptyListScreen(modifier = Modifier.padding(innerPadding))
            uiState.teamsList.isNotEmpty() -> {
                TeamsListLazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    teamsList = uiState.teamsList,
                )
            }
        }
    }
}