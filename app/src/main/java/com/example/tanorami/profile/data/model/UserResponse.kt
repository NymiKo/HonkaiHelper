package com.example.tanorami.profile.data.model

import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import com.example.tanorami.teams.data.model.TeamHeroResponse

data class UserResponse(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamHeroResponse> = emptyList(),
    val buildsHeroes: List<HeroBuildFromUserDto> = emptyList(),
)
