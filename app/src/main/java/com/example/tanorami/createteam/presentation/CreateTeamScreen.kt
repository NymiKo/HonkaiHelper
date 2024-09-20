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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
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
fun CreateTeamScreenContent(
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
        modifier = modifier.background(MaterialTheme.colorScheme.background)
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
fun HeroesListInTeam(
    modifier: Modifier = Modifier,
    heroesListInTeam: List<HeroWithNameAvatarRarity>,
) {
    LazyRow(
        modifier = modifier.padding(16.dp).height(90.dp).fillMaxWidth(),
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
fun HeroesList(
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
