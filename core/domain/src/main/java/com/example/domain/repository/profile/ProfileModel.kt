package com.example.domain.repository.profile

import com.example.domain.repository.hero_builds.model.HeroBuildFromUserModel
import com.example.domain.repository.team.model.TeamFromUserModel

data class ProfileModel(
    val nickname: String,
    val avatarUrl: String?,
    val teamsList: List<TeamFromUserModel>,
    val buildsHeroes: List<HeroBuildFromUserModel>,
)