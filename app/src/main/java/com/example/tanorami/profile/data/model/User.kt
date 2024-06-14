package com.example.tanorami.profile.data.model

import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.teams.data.model.TeamHero

data class User (
    val nickname: String = "",
    val avatarUrl: String? = "",
    val teamsList: List<TeamHero> = emptyList(),
    val buildsHeroes: List<BuildHeroWithUser> = emptyList()
)