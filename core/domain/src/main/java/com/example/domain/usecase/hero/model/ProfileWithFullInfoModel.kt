package com.example.domain.usecase.hero.model

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel

data class ProfileWithFullInfoModel(
    val nickname: String = "",
    val avatarUrl: String? = "",
    val teamsList: List<TeamHeroModel> = emptyList(),
    val buildsHeroes: List<HeroBuildModel> = emptyList(),
)
