package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.core.base.UiState
import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser

data class BuildsHeroFromUsersScreenUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val uid: String = "",
    val hero: HeroBaseInfoProjection? = null,
    val buildsList: List<BuildHeroWithUser> = emptyList(),
    val refreshingBuildsList: Boolean = false,
) : UiState
