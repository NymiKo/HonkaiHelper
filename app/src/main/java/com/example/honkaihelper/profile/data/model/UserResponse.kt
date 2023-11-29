package com.example.honkaihelper.profile.data.model

import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHero
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.data.model.TeamHeroResponse

data class UserResponse(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamHeroResponse> = emptyList(),
    val buildsHeroes: List<BuildHero> = emptyList()
)
