package com.example.tanorami.profile.data.model

import com.example.tanorami.builds_hero_from_users.data.model.BuildHero
import com.example.tanorami.teams.data.model.TeamHeroResponse

data class UserResponse(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamHeroResponse> = emptyList(),
    val buildsHeroes: List<BuildHero> = emptyList()
)
