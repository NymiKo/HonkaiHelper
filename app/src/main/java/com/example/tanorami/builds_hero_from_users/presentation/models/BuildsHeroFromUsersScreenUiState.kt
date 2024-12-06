package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.base.UiState
import com.example.common.HeroBuildModel

data class BuildsHeroFromUsersScreenUiState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val tokenUser: String = "",
    val uid: String = "",
    val hero: com.example.common.HeroBaseInfoModel? = null,
    val buildsList: List<HeroBuildModel> = emptyList(),
    val refreshingBuildsList: Boolean = false,
) : UiState
