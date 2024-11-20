package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.base.UiState
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser

data class BuildsHeroFromUsersScreenUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val uid: String = "",
    val hero: HeroBaseInfoModel? = null,
    val buildsList: List<BuildHeroWithUser> = emptyList(),
    val refreshingBuildsList: Boolean = false,
) : UiState
