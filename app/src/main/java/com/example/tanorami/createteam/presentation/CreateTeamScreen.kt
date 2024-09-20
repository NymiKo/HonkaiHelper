package com.example.tanorami.createteam.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.createteam.presentation.components.ItemHeroAvatar
import com.example.tanorami.createteam.presentation.components.ItemHeroAvatarWithName
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

@Composable
fun CreateTeamScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateTeamViewModel,
) {
    CreateTeamScreenContent(
        modifier = modifier,
        uiState = viewModel.uiState
    )
}

@Composable
fun CreateTeamScreenContent(
    modifier: Modifier = Modifier,
    uiState: CreateTeamScreenUiState,
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            HeroesListInTeam(heroesListInTeam = uiState.heroesListInTeam)
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )
            HeroesList(heroesList = uiState.heroesList)
        }
    }
}

@Composable
fun HeroesListInTeam(
    modifier: Modifier = Modifier,
    heroesListInTeam: List<HeroWithNameAvatarRarity>,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            count = heroesListInTeam.size,
            key = { heroesListInTeam[it].id },
        ) {
            ItemHeroAvatar(heroWithNameAvatarRarity = heroesListInTeam[it])
        }
    }
}

@Composable
fun HeroesList(
    modifier: Modifier = Modifier,
    heroesList: List<ActiveHeroInTeam>,
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
            ItemHeroAvatarWithName(activeHeroInTeam = heroesList[it])
        }
    }
}
