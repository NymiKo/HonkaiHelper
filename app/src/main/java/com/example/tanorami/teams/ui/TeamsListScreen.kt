package com.example.tanorami.teams.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseTopAppBar
import com.example.tanorami.createteam.ui.CreateTeamFragment
import com.example.tanorami.profile.presentation.components.TeamItem
import com.example.tanorami.teams.presentation.TeamsListViewModel
import com.example.tanorami.teams.presentation.models.TeamsListScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsListScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsListScreenUiState
import com.example.tanorami.teams.ui.components.EmptyListScreen
import com.example.tanorami.teams.ui.components.ErrorScreen
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun TeamsListScreen(
    idHero: Int,
    uid: String?,
    viewModel: TeamsListViewModel,
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
                title = if (uiState.uid == "")
                    stringResource(id = R.string.team_for_hero, uiState.nameHero)
                else stringResource(
                    id = R.string.team_for_uid
                ),
                onBack = { onEvent(TeamsListScreenEvents.OnBack) }
            )
        },
        floatingActionButton = {
            if (uiState.tokenUser.isNotEmpty()) {
                FloatingActionButton(
                    modifier = Modifier,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClick = {
                        onEvent(TeamsListScreenEvents.OnCreateTeamScreen)
                    }
                ) {
                    Icon(imageVector = Icons.Default.GroupAdd, contentDescription = null)
                }
            }
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {}
            uiState.isError -> ErrorScreen(modifier = Modifier.padding(innerPadding))
            uiState.teamsList.isEmpty() -> EmptyListScreen(modifier = Modifier.padding(innerPadding))
            uiState.teamsList.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = uiState.teamsList,
                        key = { team -> team.idTeam }
                    ) { team ->
                        TeamItem(
                            team = team,
                            clickable = false,
                            onEditTeamScreen = {}
                        )
                    }
                }
            }
        }
    }
}