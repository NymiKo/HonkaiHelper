package com.example.tanorami.profile.data.model

import com.example.common.HeroBuildModel
import com.example.common.TeamHeroModel

data class User(
    val nickname: String = "",
    val avatarUrl: String? = "",
    val teamsList: List<TeamHeroModel> = emptyList(),
    val buildsHeroes: List<HeroBuildModel> = emptyList(),
)