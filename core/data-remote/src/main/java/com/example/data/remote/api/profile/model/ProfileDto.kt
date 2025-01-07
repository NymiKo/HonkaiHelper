package com.example.data.remote.api.profile.model

import com.example.data.remote.api.build.model.HeroBuildFromUserDto
import com.example.data.remote.api.team.model.TeamFromUserDto

data class ProfileDto(
    val login: String,
    val avatarUrl: String?,
    val teamsList: List<TeamFromUserDto>,
    val buildsHeroes: List<HeroBuildFromUserDto>,
)
