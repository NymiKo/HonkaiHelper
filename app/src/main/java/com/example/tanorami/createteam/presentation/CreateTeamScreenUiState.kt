package com.example.tanorami.createteam.presentation

import com.example.tanorami.R
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity

data class CreateTeamScreenUiState(
    val idTeam: Long = -1L,
    val isSuccess: Boolean = false,
    val isTeamDeleted: Boolean = false,
    val isError: Boolean = false,
    val message: Int = R.string.error,
    val heroesList: List<ActiveHeroInTeam> = emptyList(),
    val heroesListInTeam: List<HeroWithNameAvatarRarity> = emptyList(),
    val uidTeam: String = "",
    val isCreateTeamMode: Boolean = idTeam == -1L,
)
