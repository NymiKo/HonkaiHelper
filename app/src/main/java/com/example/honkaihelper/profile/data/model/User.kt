package com.example.honkaihelper.profile.data.model

import com.example.honkaihelper.teams.data.model.TeamHero

data class User(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamHero>?
)
