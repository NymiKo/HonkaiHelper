package com.example.tanorami.teams.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.strings.R
import com.example.tanorami.teams.presentation.TeamsFromUsersViewModel
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenEvents
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenSideEffects
import com.example.tanorami.teams.presentation.models.TeamsFromUsersScreenUiState
import com.example.tanorami.teams.ui.components.EmptyListScreen
import com.example.tanorami.teams.ui.components.ErrorScreen
import com.example.tanorami.teams.ui.components.TeamsListLazyColumn
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.ui.components.top_app_bar.BaseTopAppBar
import kotlinx.serialization.Serializable

@Serializable
data class TeamsFromUsersNavArguments(val idHero: Int)

@Composable
fun TeamsFromUsersScreen(
    navArguments: TeamsFromUsersNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: TeamsFromUsersViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    TeamsListScreenContents(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        TeamsFromUsersScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(
                    TeamsFromUsersScreenEvents.GetTeamsFromUsers(
                        navArguments.idHero
                    )
                )
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TeamsListScreenContents(
    uiState: TeamsFromUsersScreenUiState,
    onEvent: (TeamsFromUsersScreenEvents) -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = uiState.refreshingTeamsList,
        onRefresh = { onEvent(TeamsFromUsersScreenEvents.RefreshTeamsList) }
    )

    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = if (uiState.nameHero.isNotEmpty()) stringResource(
                    id = R.string.team_for_hero,
                    uiState.nameHero
                ) else "",
                onBack = { onEvent(TeamsFromUsersScreenEvents.OnBack) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.pullRefresh(refreshState)
        ) {
            when {
                uiState.isError -> ErrorScreen(modifier = Modifier.padding(innerPadding))
                uiState.teamsList.isEmpty() && !uiState.refreshingTeamsList -> EmptyListScreen(
                    modifier = Modifier.padding(
                        innerPadding
                    )
                )

                uiState.teamsList.isNotEmpty() -> {
                    TeamsListLazyColumn(
                        modifier = Modifier.padding(innerPadding),
                        teamsList = uiState.teamsList,
                    )
                }
            }

            PullRefreshIndicator(
                uiState.refreshingTeamsList,
                refreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}