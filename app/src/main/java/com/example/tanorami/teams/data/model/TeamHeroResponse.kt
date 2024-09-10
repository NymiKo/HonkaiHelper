package com.example.tanorami.teams.data.model

data class TeamHeroResponse(
    val idTeam: Long,
    val idHeroOne: Int,
    val idHeroTwo: Int,
    val idHeroThree: Int,
    val idHeroFour: Int,
    val nickname: String? = null,
    val avatar: String? = null,
    val uid: String = ""
)