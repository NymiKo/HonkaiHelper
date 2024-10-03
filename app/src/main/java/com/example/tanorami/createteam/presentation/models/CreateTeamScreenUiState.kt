package com.example.tanorami.createteam.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

data class CreateTeamScreenUiState(
    val idTeam: Long = -1L,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val heroesList: List<ActiveHeroInTeam> = emptyList(),
    val heroesListInTeam: List<HeroWithNameAvatarRarity> = emptyList(),
    val uidTeam: String = "",
    val isCreateTeamMode: Boolean = idTeam == -1L,
) : UiState
