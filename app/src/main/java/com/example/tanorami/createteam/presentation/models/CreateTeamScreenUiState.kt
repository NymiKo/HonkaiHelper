package com.example.tanorami.createteam.presentation.models

import com.example.core.base.UiState
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam

data class CreateTeamScreenUiState(
    val idTeam: Long = -1L,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val heroesList: List<ActiveHeroInTeam> = emptyList(),
    val heroesListInTeam: List<HeroBaseInfoModel> = emptyList(),
    val uidTeam: String = "",
    val isCreateTeamMode: Boolean = idTeam == -1L,
    val dialogDeleteTeamVisibilityState: Boolean = false,
) : UiState
