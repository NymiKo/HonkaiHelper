package com.example.tanorami.createteam.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseSaveAlertDialog
import com.example.tanorami.base_components.BaseSaveFloatingButton
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.createteam.presentation.components.ItemHeroAvatar
import com.example.tanorami.createteam.presentation.components.ItemHeroAvatarWithName
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun CreateTeamScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateTeamViewModel,
    idTeam: Long,
) {


    CreateTeamScreenContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        idTeam = idTeam,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun CreateTeamScreenContent(
    modifier: Modifier = Modifier,
    uiState: CreateTeamScreenUiState,
    idTeam: Long,
    onEvent: (CreateTeamScreenEvents) -> Unit
) {
    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                onEvent(CreateTeamScreenEvents.GetTeam(idTeam = idTeam))
            }

            else -> {}
        }
    }

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        floatingActionButton = {
            SaveAndUpdateTeamButton(
                isCreateTeam = uiState.isCreateTeamMode,
                isSuccess = uiState.isSuccess,
                saveTeam = {},
                updateTeam = {},
                onBack = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            HeroesListInTeam(heroesListInTeam = uiState.heroesListInTeam)
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            HeroesList(
                heroesList = uiState.heroesList,
                onEvent = onEvent::invoke
            )
        }
    }
}

@Composable
private fun HeroesListInTeam(
    modifier: Modifier = Modifier,
    heroesListInTeam: List<HeroWithNameAvatarRarity>,
) {
    LazyRow(
        modifier = modifier
            .padding(16.dp)
            .height(90.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        items(
            count = heroesListInTeam.size,
            key = { heroesListInTeam[it].id },
        ) {
            ItemHeroAvatar(
                modifier = Modifier.animateItem(),
                heroWithNameAvatarRarity = heroesListInTeam[it]
            )
        }
    }
}

@Composable
private fun HeroesList(
    modifier: Modifier = Modifier,
    heroesList: List<ActiveHeroInTeam>,
    onEvent: (CreateTeamScreenEvents) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            count = heroesList.size,
            key = { heroesList[it].hero.id }
        ) {
            ItemHeroAvatarWithName(
                activeHeroInTeam = heroesList[it],
                onEvent = onEvent::invoke
            )
        }
    }
}

@Composable
private fun SaveAndUpdateTeamButton(
    modifier: Modifier = Modifier,
    isCreateTeam: Boolean,
    isSuccess: Boolean,
    saveTeam: () -> Unit,
    updateTeam: () -> Unit,
    onBack: () -> Unit,
) {
    var openSaveTeamDialog by remember { mutableStateOf(false) }

    BaseSaveFloatingButton(
        modifier = modifier,
        onClick = { openSaveTeamDialog = true }
    )

    if (openSaveTeamDialog) {
        BaseSaveAlertDialog(
            message = if (isCreateTeam) R.string.add_the_created_command else R.string.update_the_command,
            onConfirmation = {
                if (isCreateTeam) {
                    saveTeam()
                } else {
                    updateTeam()
                }
                if (isSuccess) {
                    openSaveTeamDialog = false
                    onBack()
                }
            },
            onDismissRequest = { openSaveTeamDialog = false }
        )
    }
}
