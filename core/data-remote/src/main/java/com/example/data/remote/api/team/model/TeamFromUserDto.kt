package com.example.data.remote.api.team.model

data class TeamFromUserDto(
    val idTeam: Long,
    val idHeroOne: Int,
    val idHeroTwo: Int,
    val idHeroThree: Int,
    val idHeroFour: Int,
    val uid: String = "",
    val nickname: String? = "",
    val avatar: String? = "",
)
