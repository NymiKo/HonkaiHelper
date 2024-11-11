package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.tanorami.base.UiState
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.core.database.models.hero.HeroWithNameAvatarRarity

data class BuildsHeroFromUsersScreenUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val uid: String = "",
    val hero: HeroWithNameAvatarRarity? = null,
    val buildsList: List<BuildHeroWithUser> = emptyList(),
    val refreshingBuildsList: Boolean = false,
) : UiState
