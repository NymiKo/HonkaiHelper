package com.example.honkaihelper.profile.data.model

import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.teams.data.model.TeamHero

data class User(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamHero>?,
    val buildsHeroes: List<BuildHeroWithUser>?
)
