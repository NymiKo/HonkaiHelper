package com.example.tanorami.teams.data.model

data class TeamHeroResponse(
    val idTeam: Long,
    val idHeroOne: Int,
    val idHeroTwo: Int,
    val idHeroThree: Int,
    val idHeroFour: Int,
    val uid: String = "",
    val nickname: String,
    val avatar: String,
)