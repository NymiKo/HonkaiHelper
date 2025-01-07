package com.example.data.source.profile.mapper

import com.example.data.remote.api.profile.model.ProfileDto
import com.example.data.source.hero_build.mapper.toHeroBuildsFromUserModel
import com.example.data.source.team.mapper.toTeamFromUserModel
import com.example.domain.repository.profile.ProfileModel

fun ProfileDto.toProfileModel() = ProfileModel(
    login,
    avatarUrl,
    teamsList.map { it.toTeamFromUserModel() },
    buildsHeroes.map { it.toHeroBuildsFromUserModel() }
)